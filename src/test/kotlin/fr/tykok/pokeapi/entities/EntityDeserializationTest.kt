package fr.tykok.pokeapi.entities

import fr.tykok.pokeapi.entities.contests.ContestType
import fr.tykok.pokeapi.entities.encounters.EncounterMethod
import fr.tykok.pokeapi.entities.evolution.EvolutionChain
import fr.tykok.pokeapi.entities.games.Generation
import fr.tykok.pokeapi.entities.items.Item
import fr.tykok.pokeapi.entities.locations.Location
import fr.tykok.pokeapi.entities.machines.Machine
import fr.tykok.pokeapi.entities.pokemon.Pokemon
import fr.tykok.pokeapi.http.JacksonUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class EntityDeserializationTest {
    private fun fixture(name: String): String =
        checkNotNull(javaClass.getResourceAsStream("/fixtures/$name")) { "missing fixture $name" }
            .bufferedReader()
            .readText()

    private inline fun <reified T> read(name: String): T = JacksonUtils.mapper.readValue(fixture(name), T::class.java)

    @Test
    fun `a recursive evolution chain deserializes at every depth`() {
        val chain = read<EvolutionChain>("evolution-chain-2.json")

        assertNotNull(chain.chain.species)
        assertTrue(chain.chain.evolvesTo.isNotEmpty(), "the first link must carry successors")
        val secondLevel = chain.chain.evolvesTo.first()
        assertTrue(secondLevel.evolvesTo.isNotEmpty(), "the recursive ChainLink must survive a second level")
    }

    @Test
    fun `snake case keys map onto camel case properties through the interface annotation`() {
        val item = read<Item>("item-master-ball.json")

        assertEquals("master-ball", item.name)
        // master-ball itself returns null for both fling_power and cost, so those two
        // fields cannot prove the mapping. flavor_text_entries and game_indices are
        // both multi-word snake_case keys that ARE populated for this fixture, so use
        // those to prove the interface's snake_case-to-camelCase mapping works.
        assertTrue(item.flavorTextEntries.isNotEmpty(), "flavor_text_entries must populate flavorTextEntries")
        assertTrue(item.gameIndices.isNotEmpty(), "game_indices must populate gameIndices")
    }

    @Test
    fun `nested NamedApiResource generics deserialize`() {
        val location = read<Location>("location-canalave-city.json")

        assertEquals("canalave-city", location.name)
        assertNotNull(location.region)
    }

    @Test
    fun `every remaining entity family deserializes`() {
        assertEquals("cool", read<ContestType>("contest-type-cool.json").name)
        assertEquals("walk", read<EncounterMethod>("encounter-method-walk.json").name)
        assertEquals(1, read<Machine>("machine-1.json").id)
        assertEquals("generation-i", read<Generation>("generation-1.json").name)
    }

    @Test
    fun `a full field pokemon fixture deserializes through the stricter mapper`() {
        // pokemon-deoxys-normal.json was committed earlier but never exercised by a test.
        val deoxys = read<Pokemon>("pokemon-deoxys-normal.json")

        assertEquals("deoxys-normal", deoxys.name)
        assertTrue(deoxys.stats.isNotEmpty(), "stats must populate")
        assertTrue(deoxys.moves.isNotEmpty(), "moves must populate")
        assertNotNull(deoxys.sprites)
    }
}
