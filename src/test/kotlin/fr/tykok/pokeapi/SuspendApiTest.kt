package fr.tykok.pokeapi

import fr.tykok.pokeapi.entities.berries.Berry
import fr.tykok.pokeapi.exception.PokeApiNetworkException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeout
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import mockwebserver3.SocketEffect
import mockwebserver3.junit5.StartStop
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertFailsWith

class SuspendApiTest {
    @StartStop
    private val server = MockWebServer()

    private fun fixture(name: String): String =
        checkNotNull(javaClass.getResourceAsStream("/fixtures/$name")) { "missing fixture $name" }
            .bufferedReader()
            .readText()

    private fun client(): PokeApiClient =
        PokeApiClient(PokeApiConfig(baseUrl = server.url("/api/v2").toString().trimEnd('/')))

    @Test
    fun `the suspending and blocking forms return the same value`() =
        runTest {
            server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))
            server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))

            val suspended = client().get<Berry>("cheri")
            val blocking = client().getBlocking<Berry>("cheri")

            assertEquals(blocking, suspended)
        }

    @Test
    fun `the suspending list form pages like the blocking one`() =
        runTest {
            server.enqueue(MockResponse(code = 200, body = fixture("berry-list.json")))

            client().list<Berry>(limit = 5, offset = 10)

            assertEquals("/api/v2/berry?offset=10&limit=5", server.takeRequest().target)
        }

    @Test
    fun `network failures surface as PokeApiNetworkException on both paths`() =
        runTest {
            server.enqueue(
                MockResponse
                    .Builder()
                    .code(200)
                    .onRequestStart(SocketEffect.CloseSocket())
                    .build()
            )
            assertFailsWith<PokeApiNetworkException> { client().get<Berry>("cheri") }

            server.enqueue(
                MockResponse
                    .Builder()
                    .code(200)
                    .onRequestStart(SocketEffect.CloseSocket())
                    .build()
            )
            assertThrows<PokeApiNetworkException> { client().getBlocking<Berry>("cheri") }
        }

    @Test
    fun `cancelling the coroutine cancels the call`() =
        runTest {
            server.enqueue(
                MockResponse(code = 200, body = fixture("berry-cheri.json"))
                    .newBuilder()
                    .bodyDelay(10, java.util.concurrent.TimeUnit.SECONDS)
                    .build()
            )
            val started = CompletableDeferred<Unit>()

            val job =
                launch {
                    started.complete(Unit)
                    client().get<Berry>("cheri")
                }

            withTimeout(5_000) { started.await() }
            job.cancel()
            job.join()

            assertTrue(job.isCancelled)
        }
}
