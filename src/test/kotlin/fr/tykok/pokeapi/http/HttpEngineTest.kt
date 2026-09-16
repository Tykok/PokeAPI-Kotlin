package fr.tykok.pokeapi.http

import fr.tykok.pokeapi.PokeApiConfig
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import mockwebserver3.junit5.StartStop
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class HttpEngineTest {
    @StartStop
    private val server = MockWebServer()

    @Test
    fun `reuses one OkHttpClient across calls`() {
        HttpEngine(PokeApiConfig()).use { engine ->
            val first = engine.client
            val second = engine.client
            assertSame(first, second)
        }
    }

    @Test
    fun `a hundred calls reuse the same connection pool`() {
        repeat(100) { server.enqueue(MockResponse(code = 200, body = "{}")) }

        HttpEngine(PokeApiConfig(baseUrl = server.url("/").toString())).use { engine ->
            repeat(100) { engine.execute(server.url("/berry/1").toString()).close() }

            assertEquals(100, server.requestCount)
            assertTrue(
                engine.client.connectionPool.connectionCount() <= 5,
                "a new client per call would leave one idle connection each"
            )
        }
    }

    @Test
    fun `close shuts the dispatcher and the connection pool down`() {
        val engine = HttpEngine(PokeApiConfig())
        val client = engine.client

        engine.close()

        assertTrue(client.dispatcher.executorService.isShutdown)
        assertEquals(0, client.connectionPool.connectionCount())
    }

    @Test
    fun `a supplied client is reused rather than replaced`() {
        val supplied = okhttp3.OkHttpClient()

        HttpEngine(PokeApiConfig(httpClient = supplied)).use { engine ->
            assertSame(
                supplied.connectionPool,
                engine.client.connectionPool,
                "newBuilder must preserve the caller's pool"
            )
        }
    }
}
