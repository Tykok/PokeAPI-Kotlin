package fr.tykok.pokeapi.http

import fr.tykok.pokeapi.PokeApiClient
import fr.tykok.pokeapi.PokeApiConfig
import fr.tykok.pokeapi.entities.berries.Berry
import fr.tykok.pokeapi.exception.PokeApiException
import fr.tykok.pokeapi.exception.PokeApiHttpException
import fr.tykok.pokeapi.exception.PokeApiNetworkException
import fr.tykok.pokeapi.exception.PokeApiParseException
import fr.tykok.pokeapi.exception.ResourceNotFoundException
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import mockwebserver3.SocketEffect
import mockwebserver3.junit5.StartStop
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ErrorMappingTest {
    @StartStop
    private val server = MockWebServer()

    private fun client(): PokeApiClient =
        PokeApiClient(PokeApiConfig(baseUrl = server.url("/api/v2").toString().trimEnd('/')))

    @Test
    fun `404 becomes ResourceNotFoundException`() {
        server.enqueue(MockResponse(code = 404, body = "Not Found"))

        val error = assertThrows<ResourceNotFoundException> { client().get<Berry>("nope") }

        assertTrue(error.message!!.contains("berry/nope"))
    }

    @Test
    fun `500 becomes PokeApiHttpException carrying the code and the body`() {
        server.enqueue(MockResponse(code = 500, body = "boom"))

        val error = assertThrows<PokeApiHttpException> { client().get<Berry>("cheri") }

        assertEquals(500, error.code)
        assertEquals("boom", error.body)
    }

    @Test
    fun `429 becomes PokeApiHttpException rather than ResourceNotFoundException`() {
        server.enqueue(MockResponse(code = 429, body = "slow down"))

        val error = assertThrows<PokeApiHttpException> { client().get<Berry>("cheri") }

        assertEquals(429, error.code)
    }

    @Test
    fun `malformed json becomes PokeApiParseException`() {
        server.enqueue(MockResponse(code = 200, body = "{ not json"))

        assertThrows<PokeApiParseException> { client().get<Berry>("cheri") }
    }

    @Test
    fun `a dropped connection becomes PokeApiNetworkException`() {
        server.enqueue(
            MockResponse
                .Builder()
                .code(200)
                .onRequestStart(SocketEffect.CloseSocket())
                .build()
        )

        assertThrows<PokeApiNetworkException> { client().get<Berry>("cheri") }
    }

    @Test
    fun `every failure is catchable as PokeApiException`() {
        listOf(
            MockResponse(code = 404, body = ""),
            MockResponse(code = 500, body = ""),
            MockResponse(code = 200, body = "{ not json")
        ).forEach { response ->
            server.enqueue(response)
            assertThrows<PokeApiException> { client().get<Berry>("cheri") }
        }
    }
}
