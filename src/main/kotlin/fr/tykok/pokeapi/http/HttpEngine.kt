package fr.tykok.pokeapi.http

import fr.tykok.pokeapi.PokeApiConfig
import fr.tykok.pokeapi.exception.PokeApiNetworkException
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
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

    fun execute(url: String): Response {
        println(url)
        return try {
            client.newCall(request(url)).execute()
        } catch (e: IOException) {
            throw PokeApiNetworkException(url = url, cause = e)
        }
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
