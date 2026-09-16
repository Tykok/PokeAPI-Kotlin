package fr.tykok.pokeapi.cache

import fr.tykok.pokeapi.PokeApiClient
import fr.tykok.pokeapi.PokeApiConfig
import fr.tykok.pokeapi.entities.berries.Berry
import fr.tykok.pokeapi.entities.pokemon.Pokemon
import fr.tykok.pokeapi.entities.pokemon.PokemonSpecies
import kotlinx.coroutines.test.runTest
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import mockwebserver3.junit5.StartStop
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import kotlin.time.Duration.Companion.hours

class CacheTest {
    @StartStop
    private val server = MockWebServer()

    @TempDir
    lateinit var tempDir: File

    private fun fixture(name: String): String =
        checkNotNull(javaClass.getResourceAsStream("/fixtures/$name")) { "missing fixture $name" }
            .bufferedReader()
            .readText()

    private fun client(cache: CacheConfig = CacheConfig.OnDisk(directory = tempDir, ttl = 24.hours)) =
        PokeApiClient(
            PokeApiConfig(
                baseUrl = server.url("/api/v2").toString().trimEnd('/'),
                cache = cache
            )
        )

    @Test
    fun `a repeated call is served from the cache`() =
        runTest {
            server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))

            client().use { client ->
                client.get<Berry>("cheri")
                client.get<Berry>("cheri")

                assertEquals(1, server.requestCount)
                assertEquals(1, client.cache.hitCount)
            }
        }

    @Test
    fun `refresh forces a network call`() =
        runTest {
            server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))
            server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))

            client().use { client ->
                client.get<Berry>("cheri")
                client.get<Berry>("cheri", refresh = true)

                assertEquals(2, server.requestCount)
            }
        }

    @Test
    fun `clearing one type leaves the others and the look-alike prefix intact`() =
        runTest {
            repeat(5) { server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json"))) }

            client().use { client ->
                runCatching { client.get<Pokemon>("pikachu") }
                runCatching { client.get<PokemonSpecies>("pikachu") }
                runCatching { client.get<Berry>("cheri") }
                val before = server.requestCount

                client.cache.clear<Pokemon>()

                runCatching { client.get<Pokemon>("pikachu") }
                runCatching { client.get<PokemonSpecies>("pikachu") }
                runCatching { client.get<Berry>("cheri") }

                assertEquals(
                    before + 1,
                    server.requestCount,
                    "only /pokemon/* should have been evicted, not /pokemon-species/* or /berry/*"
                )
            }
        }

    @Test
    fun `clear empties the whole store`() =
        runTest {
            server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))
            server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))

            client().use { client ->
                client.get<Berry>("cheri")
                client.cache.clear()
                client.get<Berry>("cheri")

                assertEquals(2, server.requestCount)
            }
        }

    @Test
    fun `a disabled cache always hits the network`() =
        runTest {
            server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))
            server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))

            client(cache = CacheConfig.Disabled).use { client ->
                client.get<Berry>("cheri")
                client.get<Berry>("cheri")

                assertEquals(2, server.requestCount)
            }
        }
}
