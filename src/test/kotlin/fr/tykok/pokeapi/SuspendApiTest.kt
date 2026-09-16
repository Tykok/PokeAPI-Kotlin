package fr.tykok.pokeapi

import fr.tykok.pokeapi.entities.berries.Berry
import fr.tykok.pokeapi.exception.PokeApiNetworkException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeout
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import mockwebserver3.SocketEffect
import mockwebserver3.junit5.StartStop
import okhttp3.Call
import okhttp3.EventListener
import okhttp3.OkHttpClient
import okhttp3.ResponseBody.Companion.asResponseBody
import okio.Buffer
import okio.ForwardingSource
import okio.buffer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.concurrent.TimeUnit
import kotlin.test.assertFailsWith
import kotlin.time.Duration.Companion.milliseconds

class SuspendApiTest {
    @StartStop
    private val server = MockWebServer()

    private fun fixture(name: String): String =
        checkNotNull(javaClass.getResourceAsStream("/fixtures/$name")) { "missing fixture $name" }
            .bufferedReader()
            .readText()

    private fun client(): PokeApiClient =
        PokeApiClient(PokeApiConfig(baseUrl = server.url("/api/v2").toString().trimEnd('/')))

    @Test
    fun `the suspending and blocking forms return the same value`() =
        runTest {
            server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))
            server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))

            val suspended = client().get<Berry>("cheri")
            val blocking = client().getBlocking<Berry>("cheri")

            assertEquals(blocking, suspended)
        }

    @Test
    fun `the suspending list form pages like the blocking one`() =
        runTest {
            server.enqueue(MockResponse(code = 200, body = fixture("berry-list.json")))

            client().list<Berry>(limit = 5, offset = 10)

            assertEquals("/api/v2/berry?offset=10&limit=5", server.takeRequest().target)
        }

    @Test
    fun `network failures surface as PokeApiNetworkException on both paths`() =
        runTest {
            server.enqueue(
                MockResponse
                    .Builder()
                    .code(200)
                    .onRequestStart(SocketEffect.CloseSocket())
                    .build()
            )
            assertFailsWith<PokeApiNetworkException> { client().get<Berry>("cheri") }

            server.enqueue(
                MockResponse
                    .Builder()
                    .code(200)
                    .onRequestStart(SocketEffect.CloseSocket())
                    .build()
            )
            assertThrows<PokeApiNetworkException> { client().getBlocking<Berry>("cheri") }
        }

    // The `(): Unit =` return type is load-bearing, not cosmetic: runBlocking returns whatever its
    // lambda returns, and this lambda's last expression is `assertFailsWith`'s result, not Unit.
    // JUnit silently SKIPS a @Test method that returns a value ("must not return a value. It will
    // not be executed") instead of failing it - the quietest possible false green - so without this
    // annotation the test simply never runs, and the suite would still look green.
    @Test
    fun `a call timeout surfaces as PokeApiNetworkException on the suspending path`(): Unit =
        // runBlocking, not runTest: this test depends on OkHttp's real callTimeout actually
        // elapsing on the wall clock. runTest advances a virtual clock instead, so
        // withTimeout(5_000) below would fire instantly and the test would never observe the
        // real 500ms callTimeout firing first. Do not change this back to runTest.
        runBlocking {
            // OkHttp's callTimeout works by cancelling the call (RealCall.cancel()), the exact
            // same signal a cancelled coroutine sends. A guard written as `!call.isCanceled()`
            // cannot tell a timeout from a cancellation and would leave this call - and this test -
            // suspended forever, so the whole test is wrapped in withTimeout to fail loudly rather
            // than hang the suite if that regresses.
            server.enqueue(
                MockResponse
                    .Builder()
                    .code(200)
                    .body(fixture("berry-cheri.json"))
                    .headersDelay(10, TimeUnit.SECONDS)
                    .build()
            )
            val timingOutClient =
                PokeApiClient(
                    PokeApiConfig(
                        baseUrl = server.url("/api/v2").toString().trimEnd('/'),
                        callTimeout = 500.milliseconds
                    )
                )

            withTimeout(5_000) {
                assertFailsWith<PokeApiNetworkException> { timingOutClient.get<Berry>("cheri") }
            }
        }

    @Test
    fun `cancelling the coroutine while waiting for headers cancels the http call`() =
        // runBlocking, not runTest: this test needs server.takeRequest(5, SECONDS) to genuinely
        // block while the launched coroutine runs concurrently and delivers the request. Under
        // runTest's virtual-time TestDispatcher, that real blocking call on the test thread would
        // starve the launched coroutine of a chance to run, so no request would ever arrive and
        // takeRequest would return null. Do not change this back to runTest.
        runBlocking {
            // runBlocking's default context is a single-threaded event loop bound to this calling
            // thread. `launch` with the default CoroutineStart only ENQUEUES the child coroutine on
            // that same loop; it is not run inline. The very next line blocks that same thread with
            // a plain Java call (server.takeRequest), which is not a coroutine suspension point, so
            // the thread never yields back to the loop and the queued child - including the
            // call.enqueue(...) that actually sends the HTTP request - never runs at all. Explicitly
            // dispatching the launch onto Dispatchers.IO puts it on a real thread pool instead, so it
            // can genuinely run concurrently with the blocking takeRequest call. This is the fix, not
            // a workaround - do not move it back to a bare `launch {}`.
            server.enqueue(
                MockResponse
                    .Builder()
                    .code(200)
                    .body(fixture("berry-cheri.json"))
                    .headersDelay(10, TimeUnit.SECONDS)
                    .build()
            )

            val canceled = CompletableDeferred<Unit>()
            val httpClient =
                OkHttpClient
                    .Builder()
                    .eventListener(
                        object : EventListener() {
                            override fun canceled(call: Call) {
                                canceled.complete(Unit)
                            }
                        }
                    ).build()
            val client =
                PokeApiClient(
                    PokeApiConfig(
                        baseUrl = server.url("/api/v2").toString().trimEnd('/'),
                        httpClient = httpClient
                    )
                )

            val job = launch(Dispatchers.IO) { client.get<Berry>("cheri") }

            // Confirms the request actually left for the server before we cancel, so this proves
            // an in-flight call was cancelled rather than one that never left the queue. Headers are
            // delayed 10s, so this cancellation is guaranteed to land while HttpEngine.withResponse's
            // continuation is still suspended waiting for them - the header-wait phase of the one
            // continuation and invokeOnCancellation that also covers the body read (see the test
            // below).
            assertNotNull(server.takeRequest(5, TimeUnit.SECONDS))

            job.cancel()
            job.join()

            // Observable HTTP behaviour, not coroutine bookkeeping: OkHttp's own EventListener
            // only calls `canceled` when `Call.cancel()` actually ran. `job.isCancelled` alone is
            // guaranteed true by the coroutine machinery even if `invokeOnCancellation` were
            // deleted, so it cannot fail for the right reason.
            withTimeout(5_000) { canceled.await() }
        }

    @Test
    fun `cancelling the coroutine mid-download cancels the http call`() =
        // runBlocking, not runTest: see the test above - this one also needs a genuinely
        // concurrent, dispatcher-driven launch and a real wall-clock wait for the response headers.
        runBlocking {
            // Headers are sent immediately; only the body is delayed. Unlike the test above, this
            // proves cancellation reaches a call that is already past the header wait and into the
            // body read. HttpEngine.withResponse runs the body read inside Callback.onResponse,
            // before ever resuming its continuation, so the same invokeOnCancellation that covers
            // the header wait in the test above stays armed here too. Before this fix (running the
            // read after resuming, with cancellation bound separately to the coroutine's Job), that
            // binding could never fire in time - the Job cannot reach a final state while blocked in
            // the very read it is supposed to abort - so cancelling here left the call running until
            // OkHttp's own read timeout instead of aborting it.
            server.enqueue(
                MockResponse(code = 200, body = fixture("berry-cheri.json"))
                    .newBuilder()
                    .bodyDelay(10, TimeUnit.SECONDS)
                    .build()
            )

            val canceled = CompletableDeferred<Unit>()
            val bodyReadStarted = CompletableDeferred<Unit>()
            // The network interceptor below wraps the body source rather than relying on
            // EventListener.responseBodyStart: OkHttp only fires that event AFTER a read call
            // returns with actual bytes, i.e. after the server's delay has already elapsed - too
            // late to prove anything about cancelling a read that is still blocked waiting.
            // Signalling from inside `read()` itself, before delegating, fires the instant
            // ResponseMapper.map asks for bytes and is about to block.
            val httpClient =
                OkHttpClient
                    .Builder()
                    .eventListener(
                        object : EventListener() {
                            override fun canceled(call: Call) {
                                canceled.complete(Unit)
                            }
                        }
                    ).addNetworkInterceptor { chain ->
                        val response = chain.proceed(chain.request())
                        val body = checkNotNull(response.body)
                        val instrumentedSource =
                            object : ForwardingSource(body.source()) {
                                override fun read(
                                    sink: Buffer,
                                    byteCount: Long
                                ): Long {
                                    bodyReadStarted.complete(Unit)
                                    return delegate.read(sink, byteCount)
                                }
                            }
                        response
                            .newBuilder()
                            .body(instrumentedSource.buffer().asResponseBody(body.contentType(), body.contentLength()))
                            .build()
                    }.build()
            val client =
                PokeApiClient(
                    PokeApiConfig(
                        baseUrl = server.url("/api/v2").toString().trimEnd('/'),
                        httpClient = httpClient
                    )
                )

            val job = launch(Dispatchers.IO) { client.get<Berry>("cheri") }

            // A real signal that the download has begun, not a sleep: this only fires once
            // ResponseMapper.map is already blocked reading the (10s-delayed) body.
            withTimeout(5_000) { bodyReadStarted.await() }

            job.cancel()
            job.join()

            withTimeout(5_000) { canceled.await() }
        }
}
