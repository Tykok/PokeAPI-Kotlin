package fr.tykok.pokeapi.entities.common

import fr.tykok.pokeapi.PokeApi
import fr.tykok.pokeapi.entities.PokeApiObject

/**
 * NamedApiResource contains the name and the url to get the object from the API resource (pokeapi.co).
 * @link https://bulbapedia.bulbagarden.net/wiki/Berry
 * @author Tykok
 * @version 1.0.0
 * @since 2022-07-27
 */
data class NamedApiResource<T : PokeApiObject>(
    /**
     * The name of the referenced resource.
     */
    val name: String,
    /**
     * The URL of the referenced resource.
     */
    val url: String? = null,
    val resource: T? = null
) : PokeApiObject

/**
 * Fetches the referenced resource, or `null` if this reference carries no [url].
 *
 * This always goes through [PokeApi.defaultClient], never the caller's own [fr.tykok.pokeapi.PokeApiClient].
 * A client built with custom interceptors, a proxy, or a different timeout loses all of that the
 * moment a link is followed through this extension.
 */
suspend inline fun <reified T : PokeApiObject> NamedApiResource<T>.get(): T? =
    url?.let { PokeApi.defaultClient.fetchAsync(url = it, type = T::class.java) }

/** Follows the reference, blocking the calling thread. */
inline fun <reified T : PokeApiObject> NamedApiResource<T>.getBlocking(): T? =
    url?.let { PokeApi.defaultClient.fetch(url = it, type = T::class.java) }
