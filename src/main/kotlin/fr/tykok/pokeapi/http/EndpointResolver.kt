package fr.tykok.pokeapi.http

import fr.tykok.pokeapi.annotation.Endpoint
import fr.tykok.pokeapi.exception.UnknownEndpointException
import java.util.concurrent.ConcurrentHashMap

/**
 * Maps an entity type to its PokeApi path segment.
 *
 * Reads [Endpoint] through `Class.getAnnotation`, which is plain Java reflection, so the
 * library needs no `kotlin-reflect` dependency. Results are memoised because a resolution
 * happens on every request and the answer never changes for a given type.
 */
@PublishedApi
internal object EndpointResolver {
    internal val paths = ConcurrentHashMap<Class<*>, String>()

    fun resolve(type: Class<*>): String =
        paths.getOrPut(type) {
            type.getAnnotation(Endpoint::class.java)?.path
                ?: throw UnknownEndpointException(className = type.simpleName)
        }
}
