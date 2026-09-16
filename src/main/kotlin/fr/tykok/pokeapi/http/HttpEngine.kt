package fr.tykok.pokeapi.http

import fr.tykok.pokeapi.PokeApiConfig
import fr.tykok.pokeapi.exception.PokeApiNetworkException
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import kotlin.coroutines.resumeWithException
import kotlin.time.toJavaDuration

/**
 * Owns the one [OkHttpClient] a [fr.tykok.pokeapi.PokeApiClient] uses.
 *
 * A client per request would give every call its own connection pool and dispatcher thread pool,
 * none of which is ever shut down; building it once here is what makes a loop over a hundred
 * resources cost one pool instead of a hundred.
 *
 * `newBuilder()` is deliberate: it shares the caller's pools and dispatcher rather than starting
 * fresh ones, so a supplied client keeps its own tuning.
 */
internal class HttpEngine(
    private val config: PokeApiConfig
) : AutoCloseable {
    val client: OkHttpClient =
        (config.httpClient ?: OkHttpClient())
            .newBuilder()
            .callTimeout(config.callTimeout.toJavaDuration())
            .build()

    fun execute(url: String): Response =
        try {
            client.newCall(request(url)).execute()
        } catch (e: IOException) {
            throw PokeApiNetworkException(url = url, cause = e)
        }

    /**
     * Runs the call without holding a thread, then runs [block] (typically a [ResponseMapper] body
     * read) against the response before ever resuming - so [block] runs under the same cancellation
     * guarantee as the header wait, not just alongside it.
     *
     * `enqueue` rather than `execute` so the coroutine suspends instead of blocking, and
     * `invokeOnCancellation` so cancelling the coroutine cancels the in-flight HTTP call rather than
     * leaving it to finish into nothing. The key point is *where* [block] runs: inside
     * [onResponse][Callback.onResponse] itself, before the continuation ever resumes. A response
     * handed back to the caller first and read afterwards would leave that later read as a blocking
     * call with no coroutine suspension point of its own to cancel - resuming the continuation
     * disarms `invokeOnCancellation` for good, whether or not anyone has read the body yet. Running
     * [block] first keeps the *same* continuation, and the *same* `invokeOnCancellation`, armed
     * across both the header wait and the body read: one guard, cancellable at any point until
     * [block] actually returns.
     *
     * The guard in [onFailure][Callback.onFailure] is on `continuation.isActive`, not
     * `call.isCanceled()`: OkHttp implements `callTimeout` by calling `RealCall.cancel()` on the
     * same call, so a timed-out call also reports `isCanceled() == true`. Guarding on the call
     * would swallow the timeout's `IOException` and leave the continuation - and its caller -
     * suspended forever. `continuation.isActive` is false only when the coroutine itself was
     * cancelled, which is the one case this guard needs to suppress.
     *
     * [block] runs on the thread OkHttp's `Callback.onResponse` is dispatched on - this engine's
     * [client]'s own dispatcher thread, not on whatever dispatcher resumed the calling coroutine.
     * That is what actually keeps the body read and the JSON parse off the caller's dispatcher;
     * there is no separate `withContext` doing that job. That thread pool itself is unbounded (the
     * default `Dispatcher`'s executor is `ThreadPoolExecutor(0, Int.MAX_VALUE, SynchronousQueue)`) -
     * what actually limits concurrency is `Dispatcher.maxRequests` (64) and, decisively for a
     * single-host client like this one, `maxRequestsPerHost` (5). `Dispatcher.finished()` - which
     * frees a call's per-host slot - runs only after `onResponse` returns, so for as long as [block]
     * runs it now counts against that per-host limit too, not just the network wait it used to cover.
     * A caller doing many concurrent requests to the same host, whether through the default client
     * or their own [OkHttpClient][PokeApiConfig.httpClient], will see that limit throttle overall
     * throughput a little more than before - a real trade-off, not a hazard, but worth knowing.
     *
     * Neither resume path needs an `onCancellation` handler to close anything: unlike a raw
     * [Response], the value [block] returns is not a resource. The `finally` below closes the
     * response itself either way - [ResponseMapper]'s own `use` already does this on the paths that
     * go through it, and [Response.close] is idempotent, but the engine should not depend on every
     * [block] remembering to close what it was handed.
     */
    suspend fun <T> withResponse(
        url: String,
        block: (Response) -> T
    ): T =
        suspendCancellableCoroutine { continuation ->
            val call = client.newCall(request(url))
            continuation.invokeOnCancellation { call.cancel() }
            call.enqueue(
                object : Callback {
                    override fun onFailure(
                        call: Call,
                        e: IOException
                    ) {
                        if (continuation.isActive) {
                            continuation.resumeWithException(PokeApiNetworkException(url = url, cause = e))
                        }
                    }

                    override fun onResponse(
                        call: Call,
                        response: Response
                    ) {
                        val result =
                            try {
                                block(response)
                            } catch (t: Throwable) {
                                // Catching Throwable, not Exception, and NOT relabelling it: this is
                                // not the "catch Exception only, let Error escape" rule from
                                // ResponseMapper being violated - that rule exists so a fatal Error
                                // is not disguised as a domain PokeApiException. Here `t` is resumed
                                // into the caller completely unchanged; nothing is disguised. The
                                // reason we must catch it at all is OkHttp: it marks this callback as
                                // having already run *before* invoking it, so `AsyncCall.run` will not
                                // route a Throwable escaping onResponse to onFailure - it rethrows on
                                // the dispatcher thread instead, and the continuation would never be
                                // resumed or cancelled. Letting `t` escape uncaught here would hang
                                // the caller forever on exactly the one case (e.g. an
                                // OutOfMemoryError while parsing a huge body) ResponseMapper
                                // deliberately does not catch. Do not also rethrow after resuming:
                                // that would kill a pooled dispatcher thread and double-report for no
                                // benefit.
                                if (continuation.isActive) continuation.resumeWithException(t)
                                return
                            } finally {
                                response.close()
                            }
                        if (continuation.isActive) continuation.resumeWith(Result.success(result))
                    }
                }
            )
        }

    fun request(url: String): Request =
        Request
            .Builder()
            .url(url)
            .header("User-Agent", config.userAgent)
            .build()

    override fun close() {
        client.dispatcher.executorService.shutdown()
        client.connectionPool.evictAll()
    }
}
