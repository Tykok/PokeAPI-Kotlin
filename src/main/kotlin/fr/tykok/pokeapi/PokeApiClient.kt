package fr.tykok.pokeapi

import com.fasterxml.jackson.core.type.TypeReference
import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.NamedApiResources
import fr.tykok.pokeapi.http.EndpointResolver
import fr.tykok.pokeapi.http.HttpEngine
import fr.tykok.pokeapi.http.JacksonUtils

/**
 * A configured entry point to the PokeApi RESTful API.
 *
 * Most callers want the [PokeApi] object, which holds a default instance. Build one of these
 * when you need your own configuration — a different base URL, your own [okhttp3.OkHttpClient].
 */
class PokeApiClient(
    @PublishedApi internal val config: PokeApiConfig = PokeApiConfig()
) : AutoCloseable {
    internal val engine: HttpEngine = HttpEngine(config)

    /** Get a resource by its id. */
    inline fun <reified T : PokeApiEndpointReference> get(id: Int): T =
        fetch(url = url<T>(id.toString()), type = T::class.java)

    /** Get a resource by its name. */
    inline fun <reified T : PokeApiEndpointReference> get(name: String): T =
        fetch(url = url<T>(name), type = T::class.java)

    /** Get a page of resources. */
    inline fun <reified T : PokeApiEndpointReference> get(
        limit: Int = 20,
        offset: Int = 20
    ): NamedApiResources<T> =
        fetchPage(
            url = "${url<T>()}?offset=$offset&limit=$limit",
            typeReference = object : TypeReference<NamedApiResources<T>>() {}
        )

    @PublishedApi
    internal inline fun <reified T : PokeApiEndpointReference> url(suffix: String? = null): String {
        val endpoint = EndpointResolver.resolve(T::class.java)
        return if (suffix == null) {
            "${config.baseUrl}/$endpoint"
        } else {
            "${config.baseUrl}/$endpoint/$suffix"
        }
    }

    @PublishedApi
    internal fun <T> fetch(
        url: String,
        type: Class<T>
    ): T {
        val response = engine.execute(url)
        return JacksonUtils.mapper.readValue(response.body?.string(), type)
    }

    @PublishedApi
    internal fun <T : PokeApiEndpointReference> fetchPage(
        url: String,
        typeReference: TypeReference<NamedApiResources<T>>
    ): NamedApiResources<T> {
        val response = engine.execute(url)
        return JacksonUtils.mapper.readValue(response.body?.string(), typeReference)
    }

    /** Releases the connection pool and the dispatcher threads. */
    override fun close() = engine.close()
}
