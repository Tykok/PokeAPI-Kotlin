package fr.tykok.pokeapi

import fr.tykok.pokeapi.cache.CacheConfig
import okhttp3.OkHttpClient
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * Everything a [PokeApiClient] needs that is not a request.
 *
 * @property baseUrl the API root. Defaults to [PokeApi.BASE_URL]; overridden only to point at a
 *                   test double.
 * @property callTimeout budget for a whole call, connection and body included.
 * @property userAgent sent on every request so PokeApi can attribute traffic.
 * @property httpClient supply your own client to add interceptors — logging, metrics, a proxy.
 *                      The library copies it with `newBuilder()` and adds only its own concerns.
 * @property cache how responses are cached locally. PokeApi's terms ask consumers to cache rather
 *                than re-fetch static data, so this defaults to [CacheConfig.OnDisk].
 */
public data class PokeApiConfig(
    val baseUrl: String = PokeApi.BASE_URL,
    val callTimeout: Duration = 30.seconds,
    val userAgent: String = "PokeAPI-Kotlin/$LIBRARY_VERSION",
    val httpClient: OkHttpClient? = null,
    val cache: CacheConfig = CacheConfig.OnDisk()
)
