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
import kotlin.coroutines.resume
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
     * Runs the call without holding a thread.
     *
     * `enqueue` rather than `execute`, so the coroutine suspends instead of blocking, and
     * `invokeOnCancellation` so cancelling the coroutine cancels the in-flight HTTP call rather than
     * leaving it to finish into nothing.
     */
    suspend fun executeAsync(url: String): Response =
        suspendCancellableCoroutine { continuation ->
            val call = client.newCall(request(url))
            continuation.invokeOnCancellation { call.cancel() }
            call.enqueue(
                object : Callback {
                    override fun onFailure(
                        call: Call,
                        e: IOException
                    ) {
                        if (!call.isCanceled()) {
                            continuation.resumeWithException(PokeApiNetworkException(url = url, cause = e))
                        }
                    }

                    override fun onResponse(
                        call: Call,
                        response: Response
                    ) {
                        continuation.resume(response)
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
