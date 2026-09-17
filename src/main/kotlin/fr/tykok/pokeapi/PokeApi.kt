package fr.tykok.pokeapi

import fr.tykok.pokeapi.cache.PokeApiCache
import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.NamedApiResources

/**
 * Default entry point to the PokeApi RESTful API.
 *
 * Delegates to a lazily created [PokeApiClient] with default configuration, so the common case
 * needs no setup. Build your own [PokeApiClient] when you need to configure anything.
 *
 * @author Tykok
 * @version 3.0
 * @see <a href="https://pokeapi.co/">PokeApi</a>
 * @see <a href="https://pokeapi.co/docs/v2">PokeApi Docs</a>
 */
public object PokeApi {
    private const val VERSION = "v2"

    public const val BASE_URL: String = "https://pokeapi.co/api/$VERSION"

    @PublishedApi
    internal val defaultClient: PokeApiClient by lazy { PokeApiClient() }

    /** Inspection and eviction for the default client's response cache. */
    public val cache: PokeApiCache get() = defaultClient.cache

    /** Get a resource by its id. */
    public suspend inline fun <reified T : PokeApiEndpointReference> get(
        id: Int,
        refresh: Boolean = false
    ): T = defaultClient.get<T>(id, refresh)

    /** Get a resource by its name. */
    public suspend inline fun <reified T : PokeApiEndpointReference> get(
        name: String,
        refresh: Boolean = false
    ): T = defaultClient.get<T>(name, refresh)

    /** Get a page of resources. */
    public suspend inline fun <reified T : PokeApiEndpointReference> list(
        limit: Int = 20,
        offset: Int = 0,
        refresh: Boolean = false
    ): NamedApiResources<T> = defaultClient.list<T>(limit = limit, offset = offset, refresh = refresh)

    /** Get a resource by its id, blocking the calling thread. */
    public inline fun <reified T : PokeApiEndpointReference> getBlocking(
        id: Int,
        refresh: Boolean = false
    ): T = defaultClient.getBlocking<T>(id, refresh)

    /** Get a resource by its name, blocking the calling thread. */
    public inline fun <reified T : PokeApiEndpointReference> getBlocking(
        name: String,
        refresh: Boolean = false
    ): T = defaultClient.getBlocking<T>(name, refresh)

    /** Get a page of resources, blocking the calling thread. */
    public inline fun <reified T : PokeApiEndpointReference> listBlocking(
        limit: Int = 20,
        offset: Int = 0,
        refresh: Boolean = false
    ): NamedApiResources<T> = defaultClient.listBlocking<T>(limit = limit, offset = offset, refresh = refresh)
}
