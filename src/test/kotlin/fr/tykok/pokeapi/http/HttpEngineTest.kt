package fr.tykok.pokeapi.http

import fr.tykok.pokeapi.PokeApiConfig
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import mockwebserver3.junit5.StartStop
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class HttpEngineTest {
    @StartStop
    private val server = MockWebServer()

    @Test
    fun `a hundred calls reuse the same connection pool`() {
        repeat(100) { server.enqueue(MockResponse(code = 200, body = "{}")) }

        HttpEngine(PokeApiConfig(baseUrl = server.url("/").toString())).use { engine ->
            repeat(100) { engine.execute(server.url("/berry/1").toString()).close() }

            assertEquals(100, server.requestCount)
            assertTrue(
                engine.client.connectionPool.connectionCount() <= 5,
                "a new client per call would leave one idle connection each"
            )
        }
    }

    @Test
    fun `close shuts the dispatcher and the connection pool down`() {
        val engine = HttpEngine(PokeApiConfig())
        val client = engine.client

        engine.close()

        assertTrue(client.dispatcher.executorService.isShutdown)
        assertEquals(0, client.connectionPool.connectionCount())
    }

    @Test
    fun `a supplied client is reused rather than replaced`() {
        val supplied = okhttp3.OkHttpClient()

        HttpEngine(PokeApiConfig(httpClient = supplied)).use { engine ->
            assertSame(
                supplied.connectionPool,
                engine.client.connectionPool,
                "newBuilder must preserve the caller's pool"
            )
        }
    }

    // `(): Unit =` is load-bearing, not cosmetic: runBlocking returns whatever its lambda
    // returns, and this lambda's last expression is assertFailsWith's result (a StackOverflowError),
    // not Unit. JUnit silently SKIPS a @Test method that returns a value instead of failing it, so
    // without this annotation the test would simply never run.
    @Test
    fun `an Error thrown from block surfaces to the suspending caller instead of hanging it`(): Unit =
        // runBlocking, not runTest: the response is delivered asynchronously by OkHttp's own
        // (real) dispatcher thread, outside runTest's virtual clock. With nothing else scheduled,
        // runTest's "advance when idle" auto-advance would jump straight to the virtual 5s
        // deadline before that real callback ever runs, failing this test via a virtual timeout
        // regardless of whether the fix below works. Do not change this back to runTest.
        runBlocking {
            // OkHttp marks this callback as already run before invoking it, so a Throwable
            // escaping onResponse is rethrown on the dispatcher thread, not routed to onFailure -
            // the continuation would never be resumed. withResponse must catch it and resume with
            // it unchanged instead of letting it escape, or this test hangs; wrapped in withTimeout
            // so a regression fails loudly rather than hanging the suite.
            server.enqueue(MockResponse(code = 200, body = "{}"))

            HttpEngine(PokeApiConfig(baseUrl = server.url("/").toString())).use { engine ->
                withTimeout(5_000) {
                    assertFailsWith<StackOverflowError> {
                        engine.withResponse(server.url("/berry/1").toString()) {
                            throw StackOverflowError("boom from block")
                        }
                    }
                }
            }
        }
}
