package fr.tykok.pokeapi.entities.common

import fr.tykok.pokeapi.entities.berries.Berry
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import mockwebserver3.junit5.StartStop
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NamedApiResourceTest {
    @StartStop
    private val server = MockWebServer()

    private fun fixture(name: String): String =
        checkNotNull(javaClass.getResourceAsStream("/fixtures/$name")) { "missing fixture $name" }
            .bufferedReader()
            .readText()

    @Test
    fun `get follows the resource's own url and deserializes the body`() {
        server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))

        val resource = NamedApiResource<Berry>(name = "cheri", url = server.url("/api/v2/berry/1").toString())

        val berry = resource.get()

        assertEquals("cheri", berry?.name)
    }

    @Test
    fun `a page with a null-url entry yields a shorter list instead of throwing`() {
        server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))

        val page =
            NamedApiResources<Berry>(
                count = 2,
                next = null,
                previous = null,
                results =
                    listOf(
                        NamedApiResource(name = "cheri", url = server.url("/api/v2/berry/1").toString()),
                        NamedApiResource(name = "no-url", url = null)
                    )
            )

        val berries = page.get()

        assertEquals(1, berries.size)
        assertEquals("cheri", berries.single().name)
    }
}
