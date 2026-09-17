package fr.tykok.pokeapi.exception

/**
 * Thrown when [fr.tykok.pokeapi.http.EndpointResolver] is asked to resolve a type carrying no
 * [fr.tykok.pokeapi.annotation.Endpoint] annotation.
 *
 * The message names the offending type and the missing annotation rather than a URL: no request
 * was ever made, so rendering one here would send whoever reads it looking for an address that
 * was never requested (see ruling R61).
 */
public class UnknownEndpointException(
    className: String
) : PokeApiException("$className carries no @Endpoint annotation, so PokeApi has no path for it")
