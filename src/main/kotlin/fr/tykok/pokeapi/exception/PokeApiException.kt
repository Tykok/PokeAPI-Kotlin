package fr.tykok.pokeapi.exception

import java.lang.Exception

public abstract class PokeApiException(
    message: String
) : Exception(message)
