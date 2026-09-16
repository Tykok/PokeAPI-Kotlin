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
     * [client]'s own dispatcher, by default a bounded pool OkHttp owns - not on whatever dispatcher
     * resumed the calling coroutine. That is what actually keeps the body read and the JSON parse
     * off the caller's dispatcher; there is no separate `withContext` doing that job. A caller who
     * supplies their own [OkHttpClient][PokeApiConfig.httpClient] with a constrained dispatcher (a
     * low `maxRequests`) will have that same pool carry the parse, not just the network wait, for as
     * long as [block] runs.
     *
     * Neither resume path needs an `onCancellation` handler to close anything: unlike a raw
     * [Response], the value [block] returns is not a resource, and by the time either resume runs,
     * [block] has already read (and, via [ResponseMapper], closed) the response - there is nothing
     * left to leak if cancellation and delivery race.
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
                            } catch (e: Exception) {
                                if (continuation.isActive) continuation.resumeWithException(e)
                                return
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
