package fr.tykok.pokeapi.exception

import java.io.IOException

/** The request never produced a response — timeout, DNS failure, connection reset. */
public class PokeApiNetworkException(
    public val url: String,
    override val cause: IOException
) : PokeApiException("Cannot reach $url: ${cause.message}")
