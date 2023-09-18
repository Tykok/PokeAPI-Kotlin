import entity.common.NamedApiResources
import entity.pokemon.Pokemon
import exception.HttpBodyResponseException
import kotlin.test.assertIs
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PokeApiTest {

    @Test
    fun `When try to get a Pokemon by existing id, should return the Pokemon`() {
        val actual = PokeApi.get<Pokemon>(id = 1)
        assertEquals("bulbasaur", actual.name)
        assertIs<Pokemon>(actual)
    }

    @Test
    fun `When try to get a Pokemon by existing name, should return the Pokemon`() {
        val actual = PokeApi.get<Pokemon>(name = "bulbasaur")
        assertIs<Pokemon>(actual)
    }

    @Test
    fun `When try to get a Pokemon by non-existing name, should throw an exception`() {
        assertThrows<HttpBodyResponseException> { PokeApi.get<Pokemon>(name = "MissingNo") }
    }

    @Test
    fun `When try to get a Pokemon by non-existing id, should throw an exception`() {
        assertThrows<HttpBodyResponseException> { PokeApi.get<Pokemon>(id = -1) }
    }

    @Test
    fun `When try to get a list of Pokemon, should return a list of Pokemon`() {
        val actual = PokeApi.get<Pokemon>(limit = 10, offset = 0)
        assertEquals(10, actual.results.size)
        assertIs<NamedApiResources<Pokemon>>(actual)
    }
}
