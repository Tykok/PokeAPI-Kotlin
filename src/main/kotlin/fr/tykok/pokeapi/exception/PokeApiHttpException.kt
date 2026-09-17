package fr.tykok.pokeapi.exception

/**
 * The API answered with a non-2xx status other than 404 — rate limiting, a server fault, an outage.
 *
 * Unlike [ResourceNotFoundException], some of these are worth retrying: 429 and 503 are transient.
 */
public class PokeApiHttpException(
    public val code: Int,
    public val url: String,
    public val body: String
) : PokeApiException("HTTP $code for $url: $body")
