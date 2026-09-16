package fr.tykok.pokeapi.http

import fr.tykok.pokeapi.PokeApiClient
import fr.tykok.pokeapi.PokeApiConfig
import fr.tykok.pokeapi.cache.CacheConfig
import fr.tykok.pokeapi.entities.berries.Berry
import fr.tykok.pokeapi.entities.encounters.EncounterMethod
import fr.tykok.pokeapi.exception.PokeApiException
import fr.tykok.pokeapi.exception.PokeApiHttpException
import fr.tykok.pokeapi.exception.PokeApiNetworkException
import fr.tykok.pokeapi.exception.PokeApiParseException
import fr.tykok.pokeapi.exception.ResourceNotFoundException
import kotlinx.coroutines.test.runTest
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

    // cache = Disabled: this class maps error responses (404, 500, 429, malformed JSON, dropped
    // connections) onto the right exception type - none of that is caching behaviour, and
    // `every failure is catchable as PokeApiException` below sends three DIFFERENT responses to
    // the SAME url in the SAME test. With the cache on, PokeApi's terms-driven default TTL turns
    // the first (cacheable) 404 into a Cache-Control: max-age=86400 response, and the second and
    // third iterations are then served that stale cached 404 instead of ever reaching the
    // network - so the 500-mapping and malformed-JSON-mapping paths silently stop being exercised.
    // The test still passed, because ResourceNotFoundException also satisfies the PokeApiException
    // supertype it asserts - it just stopped testing what its name says it tests.
    private fun client(): PokeApiClient =
        PokeApiClient(
            PokeApiConfig(
                baseUrl = server.url("/api/v2").toString().trimEnd('/'),
                cache = CacheConfig.Disabled
            )
        )

    @Test
    fun `404 becomes ResourceNotFoundException`() =
        runTest {
            server.enqueue(MockResponse(code = 404, body = "Not Found"))

            val error = assertThrows<ResourceNotFoundException> { client().get<Berry>("nope") }

            assertTrue(error.message!!.contains("berry/nope"))
        }

    @Test
    fun `500 becomes PokeApiHttpException carrying the code and the body`() =
        runTest {
            server.enqueue(MockResponse(code = 500, body = "boom"))

            val error = assertThrows<PokeApiHttpException> { client().get<Berry>("cheri") }

            assertEquals(500, error.code)
            assertEquals("boom", error.body)
        }

    @Test
    fun `429 becomes PokeApiHttpException rather than ResourceNotFoundException`() =
        runTest {
            server.enqueue(MockResponse(code = 429, body = "slow down"))

            val error = assertThrows<PokeApiHttpException> { client().get<Berry>("cheri") }

            assertEquals(429, error.code)
        }

    @Test
    fun `malformed json becomes PokeApiParseException`() =
        runTest {
            server.enqueue(MockResponse(code = 200, body = "{ not json"))

            assertThrows<PokeApiParseException> { client().get<Berry>("cheri") }
        }

    @Test
    fun `a null in a non-null numeric field becomes PokeApiParseException`() =
        runTest {
            // Well-formed JSON that violates the schema is the realistic parse failure: PokeAPI
            // serves valid JSON, but FAIL_ON_NULL_FOR_PRIMITIVES rejects a null where
            // EncounterMethod.order (a non-null Int) is expected. This must surface as
            // PokeApiParseException through the real client path, not as a raw Jackson exception.
            server.enqueue(
                MockResponse(
                    code = 200,
                    body = """{"id": 1, "name": "walk", "order": null, "names": []}"""
                )
            )

            assertThrows<PokeApiParseException> { client().get<EncounterMethod>("walk") }
        }

    @Test
    fun `a dropped connection becomes PokeApiNetworkException`() =
        runTest {
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
    fun `every failure is catchable as PokeApiException`() =
        runTest {
            listOf(
                MockResponse(code = 404, body = ""),
                MockResponse(code = 500, body = ""),
                MockResponse(code = 200, body = "{ not json")
            ).forEach { response ->
                server.enqueue(response)
                assertThrows<PokeApiException> { client().get<Berry>("cheri") }
            }

            // Every iteration hits the SAME url. With the cache disabled above this must still be
            // three real network round-trips, not one real request followed by two cache hits on a
            // stale (cacheable) 404 - which is exactly what silently happened when this class's
            // client() had no cache override and defaulted to CacheConfig.OnDisk.
            assertEquals(3, server.requestCount)
        }
}
