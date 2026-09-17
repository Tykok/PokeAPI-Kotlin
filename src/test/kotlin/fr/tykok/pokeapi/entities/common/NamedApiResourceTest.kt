package fr.tykok.pokeapi.entities.common

import fr.tykok.pokeapi.PokeApi
import fr.tykok.pokeapi.entities.berries.Berry
import kotlinx.coroutines.test.runTest
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import mockwebserver3.junit5.StartStop
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class NamedApiResourceTest {
    @StartStop
    private val server = MockWebServer()

    companion object {
        // NamedApiResource.get()/getBlocking() always go through PokeApi.defaultClient, the one
        // process-lifetime singleton over the real default CacheConfig.OnDisk() - unlike every
        // other test class, its cache directory cannot be redirected per test without either
        // mutating that singleton's construction or bypassing what this class exists to test (see
        // CacheConfig.OnDisk's KDoc and the task-11 report for why). These hooks only clear
        // leftovers this class itself wrote into that shared, machine-wide directory across
        // separate test runs (this session's own earlier runs left over a dozen stale entries
        // there). They do NOT provide isolation from a concurrently running second process, or
        // even a concurrently running second test JVM, sharing that same directory: that kind of
        // isolation is inherent to going through the default client and cannot be achieved from
        // inside one test at all - see CacheConfig.OnDisk's KDoc.
        // JUnit 5 requires @BeforeAll/@AfterAll to be static; this class keeps the default
        // per-method test instance lifecycle (@StartStop assumes a fresh MockWebServer per test
        // method), so the hooks live in a @JvmStatic companion object instead of switching the
        // class to @TestInstance(PER_CLASS).
        @JvmStatic
        @BeforeAll
        fun clearCacheBefore() = PokeApi.cache.clear()

        @JvmStatic
        @AfterAll
        fun clearCacheAfter() = PokeApi.cache.clear()
    }

    private fun fixture(name: String): String =
        checkNotNull(javaClass.getResourceAsStream("/fixtures/$name")) { "missing fixture $name" }
            .bufferedReader()
            .readText()

    @Test
    fun `get follows the resource's own url and deserializes the body`() =
        runTest {
            server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))

            val resource = NamedApiResource<Berry>(name = "cheri", url = server.url("/api/v2/berry/1").toString())

            val berry = resource.get()

            assertEquals("cheri", berry?.name)
        }

    @Test
    fun `a page with a null-url entry yields a shorter list instead of throwing`() =
        runTest {
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

    // getBlocking() had no caller anywhere in the suite before this (ruling R59) - the suspending
    // path above and this one are separate code (NamedApiResource.getBlocking() calls
    // PokeApi.defaultClient.fetch, not fetchAsync), so proving one works says nothing about the
    // other.
    @Test
    fun `getBlocking follows the resource's own url and deserializes the body`() {
        server.enqueue(MockResponse(code = 200, body = fixture("berry-cheri.json")))

        val resource = NamedApiResource<Berry>(name = "cheri", url = server.url("/api/v2/berry/1").toString())

        val berry = resource.getBlocking()

        assertEquals("cheri", berry?.name)
    }

    @Test
    fun `a page with a null-url entry yields a shorter list instead of throwing, blocking`() {
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

        val berries = page.getBlocking()

        assertEquals(1, berries.size)
        assertEquals("cheri", berries.single().name)
    }
}
