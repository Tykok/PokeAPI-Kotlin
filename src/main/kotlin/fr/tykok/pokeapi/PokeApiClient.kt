package fr.tykok.pokeapi

import com.fasterxml.jackson.core.type.TypeReference
import fr.tykok.pokeapi.cache.PokeApiCache
import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.NamedApiResources
import fr.tykok.pokeapi.http.EndpointResolver
import fr.tykok.pokeapi.http.HttpEngine
import fr.tykok.pokeapi.http.JacksonUtils
import fr.tykok.pokeapi.http.ResponseMapper

/**
 * A configured entry point to the PokeApi RESTful API.
 *
 * Most callers want the [PokeApi] object, which holds a default instance. Build one of these
 * when you need your own configuration — a different base URL, your own [okhttp3.OkHttpClient].
 */
public class PokeApiClient(
    @PublishedApi internal val config: PokeApiConfig = PokeApiConfig()
) : AutoCloseable {
    internal val engine: HttpEngine = HttpEngine(config)

    /** Inspection and eviction for this client's response cache. */
    public val cache: PokeApiCache = PokeApiCache(engine.okHttpCache)

    /** Get a resource by its id. */
    public suspend inline fun <reified T : PokeApiEndpointReference> get(
        id: Int,
        refresh: Boolean = false
    ): T = fetchAsync(url = url<T>(id.toString()), type = T::class.java, refresh = refresh)

    /** Get a resource by its name. */
    public suspend inline fun <reified T : PokeApiEndpointReference> get(
        name: String,
        refresh: Boolean = false
    ): T = fetchAsync(url = url<T>(name), type = T::class.java, refresh = refresh)

    /**
     * Get a page of resources.
     *
     * Named `list` rather than `get` because `get(id)` and a defaulted `get(limit, offset)` are both
     * callable with a single [Int], which made `get<Pokemon>(50)` silently mean "the Pokémon with id
     * 50" instead of "fifty Pokémon".
     */
    public suspend inline fun <reified T : PokeApiEndpointReference> list(
        limit: Int = 20,
        offset: Int = 0,
        refresh: Boolean = false
    ): NamedApiResources<T> =
        fetchPageAsync(
            url = "${url<T>()}?offset=$offset&limit=$limit",
            typeReference = object : TypeReference<NamedApiResources<T>>() {},
            refresh = refresh
        )

    /** Get a resource by its id, blocking the calling thread. */
    public inline fun <reified T : PokeApiEndpointReference> getBlocking(
        id: Int,
        refresh: Boolean = false
    ): T = fetch(url = url<T>(id.toString()), type = T::class.java, refresh = refresh)

    /** Get a resource by its name, blocking the calling thread. */
    public inline fun <reified T : PokeApiEndpointReference> getBlocking(
        name: String,
        refresh: Boolean = false
    ): T = fetch(url = url<T>(name), type = T::class.java, refresh = refresh)

    /** Get a page of resources, blocking the calling thread. */
    public inline fun <reified T : PokeApiEndpointReference> listBlocking(
        limit: Int = 20,
        offset: Int = 0,
        refresh: Boolean = false
    ): NamedApiResources<T> =
        fetchPage(
            url = "${url<T>()}?offset=$offset&limit=$limit",
            typeReference = object : TypeReference<NamedApiResources<T>>() {},
            refresh = refresh
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
        type: Class<T>,
        refresh: Boolean = false
    ): T = ResponseMapper.map(engine.execute(url, refresh), url) { JacksonUtils.mapper.readValue(it, type) }

    @PublishedApi
    internal fun <T : PokeApiEndpointReference> fetchPage(
        url: String,
        typeReference: TypeReference<NamedApiResources<T>>,
        refresh: Boolean = false
    ): NamedApiResources<T> =
        ResponseMapper.map(engine.execute(url, refresh), url) {
            JacksonUtils.mapper.readValue(it, typeReference)
        }

    // No withContext(Dispatchers.IO) here: engine.withResponse already runs the body read and the
    // Jackson parse inside OkHttp's own Callback.onResponse, on that call's dispatcher thread, before
    // ever resuming this coroutine - see HttpEngine.withResponse's KDoc. Wrapping that in withContext
    // would only add a redundant redispatch around a call that, from here, does nothing but suspend.
    //
    // engine.withResponse, not a header-only call: it keeps the underlying HTTP call cancellable for
    // the body read too, not just the header wait, so cancelling mid-download still aborts the call
    // instead of leaving it running into OkHttp's own read timeout.
    @PublishedApi
    internal suspend fun <T> fetchAsync(
        url: String,
        type: Class<T>,
        refresh: Boolean = false
    ): T =
        engine.withResponse(url, refresh) { response ->
            ResponseMapper.map(response, url) { JacksonUtils.mapper.readValue(it, type) }
        }

    @PublishedApi
    internal suspend fun <T : PokeApiEndpointReference> fetchPageAsync(
        url: String,
        typeReference: TypeReference<NamedApiResources<T>>,
        refresh: Boolean = false
    ): NamedApiResources<T> =
        engine.withResponse(url, refresh) { response ->
            ResponseMapper.map(response, url) {
                JacksonUtils.mapper.readValue(it, typeReference)
            }
        }

    /**
     * Releases what this client's engine created: the cache always, and the connection pool and
     * dispatcher threads only when no [PokeApiConfig.httpClient] was supplied — see
     * [fr.tykok.pokeapi.http.HttpEngine.close].
     */
    override fun close(): Unit = engine.close()
}
