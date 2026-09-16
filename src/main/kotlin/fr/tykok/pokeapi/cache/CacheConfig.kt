package fr.tykok.pokeapi.cache

import java.io.File
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours

/** How a [fr.tykok.pokeapi.PokeApiClient] caches responses. */
sealed interface CacheConfig {
    /** No caching. Every call reaches the network. */
    data object Disabled : CacheConfig

    /**
     * Caches responses on disk.
     *
     * Enabled by default because PokeApi asks consumers to cache locally rather than re-fetch
     * static data.
     *
     * @property directory where the store lives. Defaults to a subdirectory of the temp directory.
     * @property maxSize eviction threshold in bytes.
     * @property ttl how long an entry is served without revalidation.
     */
    data class OnDisk(
        val directory: File = File(System.getProperty("java.io.tmpdir"), "pokeapi-kotlin-cache"),
        val maxSize: Long = 50L * 1024 * 1024,
        val ttl: Duration = 24.hours
    ) : CacheConfig
}
