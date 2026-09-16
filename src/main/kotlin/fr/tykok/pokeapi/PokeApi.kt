package fr.tykok.pokeapi

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
object PokeApi {
    private const val VERSION = "v2"

    const val BASE_URL = "https://pokeapi.co/api/$VERSION"

    @PublishedApi
    internal val defaultClient: PokeApiClient by lazy { PokeApiClient() }

    /** Get a resource by its id. */
    suspend inline fun <reified T : PokeApiEndpointReference> get(id: Int): T = defaultClient.get<T>(id)

    /** Get a resource by its name. */
    suspend inline fun <reified T : PokeApiEndpointReference> get(name: String): T = defaultClient.get<T>(name)

    /** Get a page of resources. */
    suspend inline fun <reified T : PokeApiEndpointReference> list(
        limit: Int = 20,
        offset: Int = 0
    ): NamedApiResources<T> = defaultClient.list<T>(limit = limit, offset = offset)

    /** Get a resource by its id, blocking the calling thread. */
    inline fun <reified T : PokeApiEndpointReference> getBlocking(id: Int): T = defaultClient.getBlocking<T>(id)

    /** Get a resource by its name, blocking the calling thread. */
    inline fun <reified T : PokeApiEndpointReference> getBlocking(name: String): T = defaultClient.getBlocking<T>(name)

    /** Get a page of resources, blocking the calling thread. */
    inline fun <reified T : PokeApiEndpointReference> listBlocking(
        limit: Int = 20,
        offset: Int = 0
    ): NamedApiResources<T> = defaultClient.listBlocking<T>(limit = limit, offset = offset)
}
