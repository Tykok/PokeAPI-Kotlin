package fr.tykok.pokeapi.exception

/** The API answered successfully but the body did not match the expected shape. */
public class PokeApiParseException(
    public val url: String,
    override val cause: Throwable
) : PokeApiException("Cannot parse the response from $url: ${cause.message}")
