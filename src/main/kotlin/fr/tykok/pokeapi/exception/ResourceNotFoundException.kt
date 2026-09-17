package fr.tykok.pokeapi.exception

/**
 * The resource does not exist — a misspelled name, or an id past the end of the endpoint.
 *
 * Distinct from [PokeApiHttpException] because a caller treats it differently: it is an expected
 * outcome of a valid program, it is never worth retrying, and it maps cleanly onto a null.
 */
public class ResourceNotFoundException(
    public val url: String
) : PokeApiException("No resource at $url")
