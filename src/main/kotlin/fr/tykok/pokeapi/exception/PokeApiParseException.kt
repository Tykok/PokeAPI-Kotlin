package fr.tykok.pokeapi.exception

/** The API answered successfully but the body did not match the expected shape. */
class PokeApiParseException(
    val url: String,
    override val cause: Throwable
) : PokeApiException("Cannot parse the response from $url: ${cause.message}")
