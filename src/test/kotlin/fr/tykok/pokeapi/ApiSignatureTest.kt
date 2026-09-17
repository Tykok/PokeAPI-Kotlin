package fr.tykok.pokeapi

import fr.tykok.pokeapi.entities.berries.Berry
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.common.NamedApiResources
import fr.tykok.pokeapi.entities.common.get
import fr.tykok.pokeapi.entities.common.getBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Pins the exact signature of every public entry point `apiCheck` cannot see (ruling R58).
 *
 * `binary-compatibility-validator` omits reified-inline functions from `api/pokeapi.api` entirely,
 * so [PokeApi.get]/[PokeApi.list]/[PokeApi.getBlocking]/[PokeApi.listBlocking],
 * `PokeApi.cache.clear<T>()` and the four `NamedApiResource`/`NamedApiResources` link-following
 * extensions can each change their parameter list or return type without `apiCheck` ever noticing
 * — see the "apiCheck's blind spot" addendum in the v3 design doc and the `apiCheck` step's own
 * comment in `.github/workflows/ci.yml`.
 *
 * This file exists to fail COMPILATION instead, the moment one of those signatures moves. Each
 * function below is assigned to an explicitly-typed local via a lambda that calls it with its
 * full, current, named argument list:
 * - the lambda's declared function type pins the number and type of parameters, and the return
 *   type — a widened, narrowed, added, removed, or reordered-with-a-type-change parameter, or a
 *   changed return type, is a type mismatch here;
 * - the named arguments inside the lambda body pin the parameter *names* — a rename surfaces as
 *   "no parameter with this name" the moment the declaration and the call stop agreeing.
 *
 * None of these lambdas is ever invoked. [PokeApi.get] & co are hardwired to the real
 * [PokeApi.BASE_URL] (see [PokeApiTest]'s KDoc for why that cannot be pointed elsewhere from plain
 * production code), so actually calling one here would mean this test performs real network I/O,
 * which the suite forbids. Pinning the signature needs no more than a compile.
 */
class ApiSignatureTest {
    @Test
    fun `PokeApi's six public functions keep their pinned signatures`() {
        val getById: suspend (Int, Boolean) -> Berry =
            { id, refresh -> PokeApi.get<Berry>(id = id, refresh = refresh) }
        val getByName: suspend (String, Boolean) -> Berry =
            { name, refresh -> PokeApi.get<Berry>(name = name, refresh = refresh) }
        val list: suspend (Int, Int, Boolean) -> NamedApiResources<Berry> =
            { limit, offset, refresh -> PokeApi.list<Berry>(limit = limit, offset = offset, refresh = refresh) }
        val getByIdBlocking: (Int, Boolean) -> Berry =
            { id, refresh -> PokeApi.getBlocking<Berry>(id = id, refresh = refresh) }
        val getByNameBlocking: (String, Boolean) -> Berry =
            { name, refresh -> PokeApi.getBlocking<Berry>(name = name, refresh = refresh) }
        val listBlocking: (Int, Int, Boolean) -> NamedApiResources<Berry> =
            { limit, offset, refresh ->
                PokeApi.listBlocking<Berry>(limit = limit, offset = offset, refresh = refresh)
            }

        val pinned = listOf(getById, getByName, list, getByIdBlocking, getByNameBlocking, listBlocking)

        assertEquals(6, pinned.size)
    }

    @Test
    fun `PokeApiCache's clear T keeps its pinned signature`() {
        val clearBerry: () -> Unit = { PokeApi.cache.clear<Berry>() }

        assertEquals(1, listOf(clearBerry).size)
    }

    @Test
    fun `the NamedApiResource(s) link-following extensions keep their pinned signatures`() {
        val resource = NamedApiResource<Berry>(name = "cheri", url = null)
        val page = NamedApiResources<Berry>(count = 0, next = null, previous = null, results = emptyList())

        val getExt: suspend () -> Berry? = { resource.get() }
        val getBlockingExt: () -> Berry? = { resource.getBlocking() }
        val getPageExt: suspend () -> List<Berry> = { page.get() }
        val getBlockingPageExt: () -> List<Berry> = { page.getBlocking() }

        val pinned = listOf(getExt, getBlockingExt, getPageExt, getBlockingPageExt)

        assertEquals(4, pinned.size)
    }
}
