# Configuration

`PokeApi` is a default [`PokeApiClient`](#pokeapiclient) built with default settings, so the common
case needs no setup. Build your own `PokeApiClient` when you need to configure anything — a
different timeout, your own `OkHttpClient`, or a different cache.

```kotlin
import fr.tykok.pokeapi.PokeApiClient
import fr.tykok.pokeapi.PokeApiConfig

val client = PokeApiClient(PokeApiConfig())
```

## `PokeApiClient`

`PokeApiClient` is `AutoCloseable`. Call `close()` once you are done with an instance you built
yourself — it releases the on-disk cache, and the connection pool and dispatcher threads too,
unless you supplied your own `httpClient` (see below).

```kotlin
import fr.tykok.pokeapi.PokeApiClient
import fr.tykok.pokeapi.entities.pokemon.Pokemon

suspend fun main() {
    val client = PokeApiClient()
    try {
        val pikachu = client.get<Pokemon>(name = "pikachu")
        println(pikachu.name)
    } finally {
        client.close()
    }
}
```

## `PokeApiConfig`

| Property | Default | Meaning |
| --- | --- | --- |
| `baseUrl` | `PokeApi.BASE_URL` | the API root — override only to point at a test double |
| `callTimeout` | `30.seconds` | budget for a whole call, connection and body included |
| `userAgent` | `PokeAPI-Kotlin/<version>` | sent on every request so PokeApi can attribute traffic |
| `httpClient` | `null` | supply your own `OkHttpClient` to add interceptors — logging, metrics, a proxy |
| `cache` | `CacheConfig.OnDisk()` | how responses are cached locally |

### Custom `OkHttpClient`

The library copies whatever `OkHttpClient` you pass with `newBuilder()` and adds only its own
concerns, so your interceptors still run. Use it for request logging, since the library itself
prints nothing:

```kotlin
import fr.tykok.pokeapi.PokeApiClient
import fr.tykok.pokeapi.PokeApiConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient

val logging = Interceptor { chain ->
    val request = chain.request()
    println("${request.method} ${request.url}")
    chain.proceed(request)
}
val client = PokeApiClient(
    PokeApiConfig(httpClient = OkHttpClient.Builder().addInterceptor(logging).build())
)
```

## `CacheConfig`

Responses are cached on disk for 24 hours by default, because PokeApi's terms ask consumers to cache
locally rather than re-fetch static data.

```kotlin
import fr.tykok.pokeapi.PokeApi
import fr.tykok.pokeapi.PokeApiClient
import fr.tykok.pokeapi.PokeApiConfig
import fr.tykok.pokeapi.cache.CacheConfig
import fr.tykok.pokeapi.entities.pokemon.Pokemon

suspend fun main() {
    PokeApi.get<Pokemon>(name = "pikachu", refresh = true)   // bypass the cache for one call
    PokeApi.cache.clear()                                    // evict everything
    PokeApi.cache.clear<Pokemon>()                            // evict one endpoint
}

val client = PokeApiClient(PokeApiConfig(cache = CacheConfig.Disabled))
```

`CacheConfig.OnDisk` also takes `maxSize` (eviction threshold in bytes, default 50 MB) and `ttl`
(how long an entry is served without revalidation, default 24 hours).

!!! warning "Concurrent instances need distinct cache directories"

    The default `CacheConfig.OnDisk` directory is one fixed path under the system temp directory. If
    you run more than one client at the same time — two instances in one process, or two separate
    processes — give each its own `directory`, or they will share that directory and can desync
    each other's cache journal:

    ```kotlin
    import fr.tykok.pokeapi.PokeApiClient
    import fr.tykok.pokeapi.PokeApiConfig
    import fr.tykok.pokeapi.cache.CacheConfig
    import java.io.File

    val client = PokeApiClient(
        PokeApiConfig(cache = CacheConfig.OnDisk(directory = File("/var/myapp/pokeapi-cache-1")))
    )
    ```

    The library cannot pick a safe default for you here: it has no reliable way to tell your
    instances or processes apart, and a directory unique to each run would defeat the very
    persistence a disk cache exists for.

See [Methods](methods.md#refresh-bypass-the-cache) for the `refresh` parameter on every `get`/`list`
call, and [API Reference](entities/index.md) for the entity types you can pass to `get`/`list`.
