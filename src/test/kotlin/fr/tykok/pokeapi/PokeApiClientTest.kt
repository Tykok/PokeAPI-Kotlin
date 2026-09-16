package fr.tykok.pokeapi

import fr.tykok.pokeapi.entities.berries.Berry
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import mockwebserver3.junit5.StartStop
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PokeApiClientTest {
    @StartStop
    private val server = MockWebServer()

    private fun fixture(name: String): String =
        checkNotNull(javaClass.getResourceAsStream("/fixtures/$name")) { "missing fixture $name" }
            .bufferedReader()
            .readText()

    private fun client(): PokeApiClient =
        PokeApiClient(PokeApiConfig(baseUrl = server.url("/api/v2").toString().trimEnd('/')))

    @Test
    fun `get by name requests the endpoint path and deserializes the body`() {
        server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))

        val berry = client().get<Berry>("cheri")

        assertEquals("/api/v2/berry/cheri", server.takeRequest().target)
        assertEquals("cheri", berry.name)
    }

    @Test
    fun `get by id requests the numeric path`() {
        server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))

        client().get<Berry>(1)

        assertEquals("/api/v2/berry/1", server.takeRequest().target)
    }

    @Test
    fun `snake case json maps onto camel case properties`() {
        server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))

        val berry = client().get<Berry>("cheri")

        assertEquals(3, berry.growthTime)
        assertEquals(5, berry.maxHarvest)
    }

    @Test
    fun `get by limit and offset requests the listing path and deserializes the page`() {
        server.enqueue(MockResponse(code = 200, body = fixture("berry-list.json")))

        val page = client().get<Berry>(limit = 20, offset = 0)

        assertEquals("/api/v2/berry?offset=0&limit=20", server.takeRequest().target)
        // This does NOT prove the element type token survives fetchPage's generic erasure.
        // NamedApiResource<T> exposes no field parameterised on T that any real PokeAPI response
        // populates (`resource` is always null and never read), so an erased type token would
        // deserialize this exact same value here. This only proves the request path/query and
        // that a paged response parses.
        assertEquals("cheri", page.results.first().name)
    }

    @Test
    fun `the default config points at the public api`() {
        assertEquals("https://pokeapi.co/api/v2", PokeApiConfig().baseUrl)
        assertEquals("https://pokeapi.co/api/v2", PokeApi.BASE_URL)
    }

    @Test
    fun `the user agent carries the library version`() {
        server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))

        client().get<Berry>("cheri")

        assertEquals("PokeAPI-Kotlin/$LIBRARY_VERSION", server.takeRequest().headers["User-Agent"])
    }
}
