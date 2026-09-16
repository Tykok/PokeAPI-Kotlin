package fr.tykok.pokeapi.http

import fr.tykok.pokeapi.exception.PokeApiHttpException
import fr.tykok.pokeapi.exception.PokeApiParseException
import fr.tykok.pokeapi.exception.ResourceNotFoundException
import okhttp3.Response

/**
 * Turns a [Response] into a value or a [fr.tykok.pokeapi.exception.PokeApiException].
 *
 * Checking the status before parsing is the whole point: without it a 404's error body reaches
 * Jackson and surfaces as a deserialization failure, which tells the caller nothing.
 *
 * `use` also closes responses whose body is never read — the failure paths.
 */
internal object ResponseMapper {
    fun <T> map(
        response: Response,
        url: String,
        read: (String) -> T
    ): T =
        response.use { r ->
            val body = r.body.string()
            when {
                r.isSuccessful ->
                    // Deliberately not `runCatching`: it catches Throwable, so a fatal
                    // Error (e.g. OutOfMemoryError while parsing a huge body) would be
                    // relabeled as a PokeApiParseException, and a caller catching
                    // PokeApiException would then swallow a dying JVM as if the payload
                    // were merely malformed. Catch Exception only, and let Error escape.
                    try {
                        read(body)
                    } catch (e: Exception) {
                        throw PokeApiParseException(url = url, cause = e)
                    }
                r.code == 404 -> throw ResourceNotFoundException(url = url)
                else -> throw PokeApiHttpException(code = r.code, url = url, body = body)
            }
        }
}
