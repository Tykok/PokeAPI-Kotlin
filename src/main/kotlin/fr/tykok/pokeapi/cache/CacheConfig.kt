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
     * [directory] defaults to one fixed, machine-wide path, and OkHttp's on-disk store requires
     * exclusive access to whatever directory it is given — it implements no locking of its own.
     * Two [PokeApiClient][fr.tykok.pokeapi.PokeApiClient]s built with the default `directory` at
     * the same time, whether two instances in one process or two separate processes, share that
     * one directory and can desync each other's cache journal. This is not solved by deriving a
     * per-process default here: the library has no reliable notion of the caller's process
     * identity, and a directory unique per run would defeat the very persistence a disk cache
     * exists for. If you run more than one client concurrently — including in a test suite that
     * builds more than one client — give each its own `directory`.
     *
     * @property directory where the store lives. Defaults to a subdirectory of the temp directory;
     *                     see the constraint above when running more than one client concurrently.
     * @property maxSize eviction threshold in bytes.
     * @property ttl how long an entry is served without revalidation.
     */
    data class OnDisk(
        val directory: File = File(System.getProperty("java.io.tmpdir"), "pokeapi-kotlin-cache"),
        val maxSize: Long = 50L * 1024 * 1024,
        val ttl: Duration = 24.hours
    ) : CacheConfig
}
