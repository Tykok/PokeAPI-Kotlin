package fr.tykok.pokeapi

import fr.tykok.pokeapi.entities.berries.Berry
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import mockwebserver3.junit5.StartStop
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ListEndpointTest {
    @StartStop
    private val server = MockWebServer()

    private fun fixture(name: String): String =
        checkNotNull(javaClass.getResourceAsStream("/fixtures/$name")) { "missing fixture $name" }
            .bufferedReader()
            .readText()

    private fun client(): PokeApiClient =
        PokeApiClient(PokeApiConfig(baseUrl = server.url("/api/v2").toString().trimEnd('/')))

    @Test
    fun `list defaults to the first page`() {
        server.enqueue(MockResponse(code = 200, body = fixture("berry-list.json")))

        client().list<Berry>()

        assertEquals("/api/v2/berry?offset=0&limit=20", server.takeRequest().target)
    }

    @Test
    fun `list forwards explicit paging`() {
        server.enqueue(MockResponse(code = 200, body = fixture("berry-list.json")))

        client().list<Berry>(limit = 50, offset = 100)

        assertEquals("/api/v2/berry?offset=100&limit=50", server.takeRequest().target)
    }

    @Test
    fun `list requests the listing path and deserializes the page`() {
        server.enqueue(MockResponse(code = 200, body = fixture("berry-list.json")))

        val page = client().list<Berry>(limit = 20, offset = 0)

        assertEquals("/api/v2/berry?offset=0&limit=20", server.takeRequest().target)
        assertTrue(page.count > 0)
        assertEquals(20, page.results.size)
        // This does NOT prove the element type token survives fetchPage's generic erasure.
        // NamedApiResource<T> exposes no field parameterised on T that any real PokeAPI response
        // populates (`resource` is always null and never read), so an erased type token would
        // deserialize this exact same value here. This only proves the request path/query and
        // that a paged response parses.
        assertEquals("cheri", page.results.first().name)
    }

    @Test
    fun `a single Int argument is unambiguously an id`() {
        server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))

        client().get<Berry>(1)

        assertEquals("/api/v2/berry/1", server.takeRequest().target)
    }
}
