package fr.tykok.pokeapi.cache

import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.http.EndpointResolver
import okhttp3.Cache
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

/**
 * Inspection and eviction for the response cache.
 *
 * There is one physical store. OkHttp keys it by URL and every endpoint already has its own URL
 * prefix, so entries are separated by endpoint without a store per type; what a per-type store
 * would buy — selective invalidation — is done here by filtering urls instead.
 */
class PokeApiCache
    @PublishedApi
    internal constructor(
        @PublishedApi internal val cache: Cache?
    ) {
        /** Bytes currently held on disk. */
        val size: Long get() = cache?.size() ?: 0L

        /** Responses served from the store since this client was built. */
        val hitCount: Int get() = cache?.hitCount() ?: 0

        /** Responses that reached the network since this client was built. */
        val networkCount: Int get() = cache?.networkCount() ?: 0

        /** Evicts everything. */
        fun clear() {
            cache?.evictAll()
        }

        /** Evicts every entry of one endpoint. */
        fun clear(endpoint: String) {
            val store = cache ?: return
            val urls = store.urls()
            while (urls.hasNext()) {
                val segments = urls.next().toHttpUrlOrNull()?.pathSegments ?: continue
                // Matched against the whole list of path segments, not a string prefix: a
                // `baseUrl` carrying its own path (e.g. `/api/v2`) shifts where the endpoint
                // segment sits, and a substring/prefix compare would either miss it or, worse,
                // let clearing `pokemon` evict `pokemon-species`/`pokemon-color` because they
                // share a textual prefix. Segment equality is exact, so neither happens.
                if (segments.contains(endpoint)) {
                    urls.remove()
                }
            }
        }

        /**
         * Evicts every entry of one entity's endpoint.
         *
         * `@JvmName` is required here: an inline reified function still emits a real method, and
         * without a distinct name it would erase to the exact same JVM signature (`clear()V`) as
         * the no-arg [clear] above — a platform declaration clash that fails compilation.
         */
        @JvmName("clearEndpointOf")
        inline fun <reified T : PokeApiEndpointReference> clear() {
            clear(EndpointResolver.resolve(T::class.java))
        }
    }
