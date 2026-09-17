package fr.tykok.pokeapi

import fr.tykok.pokeapi.cache.CacheConfig
import fr.tykok.pokeapi.entities.berries.Berry
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkObject
import kotlinx.coroutines.test.runTest
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import mockwebserver3.junit5.StartStop
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Exercises the [PokeApi] facade itself (ruling R60).
 *
 * Before this class, no test called [PokeApi.get], [PokeApi.list], [PokeApi.getBlocking] or
 * [PokeApi.listBlocking] - only [PokeApi.cache]'s `clear()` - even though these six functions are
 * the ones almost every consumer calls first. They were left untested because [PokeApi] is
 * hardwired to [PokeApi.defaultClient], a lazily-created [PokeApiClient] pointed at the real
 * [PokeApi.BASE_URL], and neither may be made configurable to get around that (the constraint that
 * has held for the whole branch, and still holds here).
 *
 * [PokeApi.defaultClient] is `@PublishedApi internal`, so - like [NamedApiResourceTest], which
 * reads it directly to reach [PokeApiClient.fetchAsync]/[PokeApiClient.fetch] - this class can
 * name it at all only because the test source set is a friend module of `main`. Referencing it
 * from `every { PokeApi.defaultClient } ... }` below is exactly that: naming an internal member
 * from friend-module test code, not a reflection trick and not new production configurability.
 * `mockkObject` then swaps only what [PokeApi.defaultClient] resolves to, for the lifetime of one
 * test; every other member of the [PokeApi] object - [PokeApi.BASE_URL] included - is untouched.
 * Once resolved, the substituted value is a completely ordinary [PokeApiClient] built the same way
 * every other test in this suite builds one, so every call still runs the real code path
 * ([fr.tykok.pokeapi.http.HttpEngine], [fr.tykok.pokeapi.http.ResponseMapper], Jackson) against a
 * [MockWebServer] instead of the network - no test in this class performs real network I/O.
 */
class PokeApiTest {
    @StartStop
    private val server = MockWebServer()

    private fun fixture(name: String): String =
        checkNotNull(javaClass.getResourceAsStream("/fixtures/$name")) { "missing fixture $name" }
            .bufferedReader()
            .readText()

    // Same shape PokeApiClientTest's own client() uses: baseUrl pointed at this test's
    // MockWebServer, cache disabled so this suite never touches the shared machine-wide cache
    // directory CacheConfig.OnDisk() defaults to.
    private fun testClient(): PokeApiClient =
        PokeApiClient(
            PokeApiConfig(
                baseUrl = server.url("/api/v2").toString().trimEnd('/'),
                cache = CacheConfig.Disabled
            )
        )

    @BeforeEach
    fun redirectDefaultClient() {
        mockkObject(PokeApi)
        every { PokeApi.defaultClient } returns testClient()
    }

    @AfterEach
    fun restoreDefaultClient() {
        unmockkObject(PokeApi)
    }

    @Test
    fun `get by id behaves exactly like a default-constructed PokeApiClient`() =
        runTest {
            server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))
            server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))

            val viaFacade = PokeApi.get<Berry>(1)
            val viaClient = testClient().get<Berry>(1)

            assertEquals(viaClient, viaFacade)
        }

    @Test
    fun `get by name requests the name path and deserializes the body`() =
        runTest {
            server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))

            val berry = PokeApi.get<Berry>("cheri")

            assertEquals("/api/v2/berry/cheri", server.takeRequest().target)
            assertEquals("cheri", berry.name)
        }

    @Test
    fun `list pages exactly like a default-constructed PokeApiClient's list`() =
        runTest {
            server.enqueue(MockResponse(code = 200, body = fixture("berry-list.json")))
            server.enqueue(MockResponse(code = 200, body = fixture("berry-list.json")))

            val viaFacade = PokeApi.list<Berry>(limit = 5, offset = 10)
            val viaClient = testClient().list<Berry>(limit = 5, offset = 10)

            assertEquals(viaClient, viaFacade)
            assertEquals("/api/v2/berry?offset=10&limit=5", server.takeRequest().target)
            assertEquals("/api/v2/berry?offset=10&limit=5", server.takeRequest().target)
        }

    @Test
    fun `getBlocking by id behaves exactly like a default-constructed PokeApiClient's getBlocking`() {
        server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))
        server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))

        val viaFacade = PokeApi.getBlocking<Berry>(1)
        val viaClient = testClient().getBlocking<Berry>(1)

        assertEquals(viaClient, viaFacade)
    }

    @Test
    fun `getBlocking by name requests the name path`() {
        server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))

        PokeApi.getBlocking<Berry>("cheri")

        assertEquals("/api/v2/berry/cheri", server.takeRequest().target)
    }

    @Test
    fun `listBlocking pages the same way as list`() {
        server.enqueue(MockResponse(code = 200, body = fixture("berry-list.json")))

        PokeApi.listBlocking<Berry>(limit = 5, offset = 10)

        assertEquals("/api/v2/berry?offset=10&limit=5", server.takeRequest().target)
    }
}
