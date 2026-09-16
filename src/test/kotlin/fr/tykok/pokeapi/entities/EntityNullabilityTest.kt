package fr.tykok.pokeapi.entities

import fr.tykok.pokeapi.entities.moves.Move
import fr.tykok.pokeapi.http.JacksonUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class EntityNullabilityTest {
    private fun fixture(name: String): String =
        checkNotNull(javaClass.getResourceAsStream("/fixtures/$name")) { "missing fixture $name" }
            .bufferedReader()
            .readText()

    @Test
    fun `a status move deserializes with a null power`() {
        val growl = JacksonUtils.mapper.readValue(fixture("move-growl.json"), Move::class.java)

        // Growl (id 45) is a status move: PokeAPI returns power = null for it.
        assertNull(growl.power)
        // Growl's accuracy, unlike its power, is filled in by the API (100), so it must
        // deserialize as a present Int. (Other moves, e.g. swords-dance, do return
        // accuracy = null, which is why the field's type is still Int? below.)
        assertEquals(100, growl.accuracy)
        assertEquals("growl", growl.name)
    }

    @Test
    fun `numeric identifiers are plain Int`() {
        val growl = JacksonUtils.mapper.readValue(fixture("move-growl.json"), Move::class.java)

        val id: Int = growl.id
        assertEquals(45, id)
    }

    @Test
    fun `a self-buff move deserializes with a null accuracy`() {
        val harden = JacksonUtils.mapper.readValue(fixture("move-harden.json"), Move::class.java)

        // Harden (id 106) never misses: PokeAPI returns accuracy = null for it.
        assertNull(harden.accuracy)
        assertEquals("harden", harden.name)
    }
}
