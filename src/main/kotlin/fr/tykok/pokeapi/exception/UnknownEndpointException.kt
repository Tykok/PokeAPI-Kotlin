package fr.tykok.pokeapi.exception

import fr.tykok.pokeapi.PokeApi

public class UnknownEndpointException(
    className: String
) : PokeApiException("Unknown endpoint: ${PokeApi.BASE_URL}/$className")
