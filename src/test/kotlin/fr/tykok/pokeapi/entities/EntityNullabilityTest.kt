package fr.tykok.pokeapi.entities

import fr.tykok.pokeapi.entities.moves.Move
import fr.tykok.pokeapi.entities.pokemon.GrowthRateExperienceLevel
import fr.tykok.pokeapi.http.JacksonUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertThrows
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

    @Test
    fun `a past move stat entry deserializes with null accuracy, power, effect chance and type`() {
        // swords-dance (id 14) carries one past_values entry (version group x-y) where
        // PokeAPI returns null for accuracy, power, effect_chance AND type. Observed
        // against the live API. If any of these four fields were reverted to a
        // non-null Int/NamedApiResource, this fixture would throw instead of
        // deserializing (FAIL_ON_NULL_FOR_PRIMITIVES / a null into a non-null
        // reference type), so this assertion would fail.
        val swordsDance = JacksonUtils.mapper.readValue(fixture("move-swords-dance.json"), Move::class.java)

        assertEquals(1, swordsDance.pastValues.size)
        val pastValue = swordsDance.pastValues.first()

        assertNull(pastValue.accuracy)
        assertNull(pastValue.power)
        assertNull(pastValue.effectChance)
        assertNull(pastValue.type)
        // pp and versionGroup are NOT part of the bug: they stay populated and non-null.
        assertEquals(30, pastValue.pp)
        assertEquals("x-y", pastValue.versionGroup.name)
    }

    @Test
    fun `a null read into a non-null numeric field is rejected rather than read as zero`() {
        // GrowthRateExperienceLevel.experience is a non-null Int. Without
        // FAIL_ON_NULL_FOR_PRIMITIVES, Jackson would silently coerce this JSON null
        // to 0, masking the fact that the API never sent a value for the field.
        val json = """{"level": 1, "experience": null}"""

        assertThrows(Exception::class.java) {
            JacksonUtils.mapper.readValue(json, GrowthRateExperienceLevel::class.java)
        }
    }
}
