package fr.tykok.pokeapi.exception

/**
 * The API answered with a non-2xx status other than 404 — rate limiting, a server fault, an outage.
 *
 * Unlike [ResourceNotFoundException], some of these are worth retrying: 429 and 503 are transient.
 */
class PokeApiHttpException(
    val code: Int,
    val url: String,
    val body: String
) : PokeApiException("HTTP $code for $url: $body")
