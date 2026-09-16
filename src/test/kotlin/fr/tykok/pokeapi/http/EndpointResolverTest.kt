package fr.tykok.pokeapi.http

import fr.tykok.pokeapi.entities.berries.Berry
import fr.tykok.pokeapi.entities.berries.BerryFirmness
import fr.tykok.pokeapi.entities.berries.BerryFlavor
import fr.tykok.pokeapi.entities.contests.ContestEffect
import fr.tykok.pokeapi.entities.contests.ContestType
import fr.tykok.pokeapi.entities.contests.SuperContestEffects
import fr.tykok.pokeapi.entities.encounters.EncounterCondition
import fr.tykok.pokeapi.entities.encounters.EncounterConditionValue
import fr.tykok.pokeapi.entities.encounters.EncounterMethod
import fr.tykok.pokeapi.entities.evolution.EvolutionChain
import fr.tykok.pokeapi.entities.evolution.EvolutionTrigger
import fr.tykok.pokeapi.entities.games.Generation
import fr.tykok.pokeapi.entities.games.Pokedex
import fr.tykok.pokeapi.entities.games.Version
import fr.tykok.pokeapi.entities.games.VersionGroup
import fr.tykok.pokeapi.entities.items.Item
import fr.tykok.pokeapi.entities.items.ItemAttribute
import fr.tykok.pokeapi.entities.items.ItemCategory
import fr.tykok.pokeapi.entities.items.ItemFlingEffect
import fr.tykok.pokeapi.entities.items.ItemPocket
import fr.tykok.pokeapi.entities.locations.Location
import fr.tykok.pokeapi.entities.locations.LocationArea
import fr.tykok.pokeapi.entities.locations.PalParkArea
import fr.tykok.pokeapi.entities.locations.Region
import fr.tykok.pokeapi.entities.machines.Machine
import fr.tykok.pokeapi.entities.moves.Move
import fr.tykok.pokeapi.entities.moves.MoveAilment
import fr.tykok.pokeapi.entities.moves.MoveBattleStyle
import fr.tykok.pokeapi.entities.moves.MoveCategory
import fr.tykok.pokeapi.entities.moves.MoveDamageClass
import fr.tykok.pokeapi.entities.moves.MoveLearnMethod
import fr.tykok.pokeapi.entities.moves.MoveTarget
import fr.tykok.pokeapi.entities.pokemon.Ability
import fr.tykok.pokeapi.entities.pokemon.Characteristic
import fr.tykok.pokeapi.entities.pokemon.EggGroup
import fr.tykok.pokeapi.entities.pokemon.Gender
import fr.tykok.pokeapi.entities.pokemon.GrowthRate
import fr.tykok.pokeapi.entities.pokemon.Nature
import fr.tykok.pokeapi.entities.pokemon.PokeathlonStat
import fr.tykok.pokeapi.entities.pokemon.Pokemon
import fr.tykok.pokeapi.entities.pokemon.PokemonColor
import fr.tykok.pokeapi.entities.pokemon.PokemonForm
import fr.tykok.pokeapi.entities.pokemon.PokemonHabitat
import fr.tykok.pokeapi.entities.pokemon.PokemonLocationArea
import fr.tykok.pokeapi.entities.pokemon.PokemonShape
import fr.tykok.pokeapi.entities.pokemon.PokemonSpecies
import fr.tykok.pokeapi.entities.pokemon.Stat
import fr.tykok.pokeapi.entities.pokemon.Type
import fr.tykok.pokeapi.exception.UnknownEndpointException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class EndpointResolverTest {
    private val expected: List<Pair<Class<*>, String>> =
        listOf(
            Berry::class.java to "berry",
            BerryFirmness::class.java to "berry-firmness",
            BerryFlavor::class.java to "berry-flavor",
            ContestType::class.java to "contest-type",
            ContestEffect::class.java to "contest-effect",
            SuperContestEffects::class.java to "super-contest-effect",
            EncounterMethod::class.java to "encounter-method",
            EncounterCondition::class.java to "encounter-condition",
            EncounterConditionValue::class.java to "encounter-condition-value",
            EvolutionChain::class.java to "evolution-chain",
            EvolutionTrigger::class.java to "evolution-trigger",
            Generation::class.java to "generation",
            Pokedex::class.java to "pokedex",
            Version::class.java to "version",
            VersionGroup::class.java to "version-group",
            Item::class.java to "item",
            ItemAttribute::class.java to "item-attribute",
            ItemCategory::class.java to "item-category",
            ItemFlingEffect::class.java to "item-fling-effect",
            ItemPocket::class.java to "item-pocket",
            Location::class.java to "location",
            LocationArea::class.java to "location-area",
            PalParkArea::class.java to "pal-park-area",
            Region::class.java to "region",
            Machine::class.java to "machine",
            Move::class.java to "move",
            MoveAilment::class.java to "move-ailment",
            MoveBattleStyle::class.java to "move-battle-style",
            MoveCategory::class.java to "move-category",
            MoveDamageClass::class.java to "move-damage-class",
            MoveLearnMethod::class.java to "move-learn-method",
            MoveTarget::class.java to "move-target",
            Pokemon::class.java to "pokemon",
            Ability::class.java to "ability",
            Characteristic::class.java to "characteristic",
            EggGroup::class.java to "egg-group",
            Gender::class.java to "gender",
            GrowthRate::class.java to "growth-rate",
            Nature::class.java to "nature",
            PokeathlonStat::class.java to "pokeathlon-stat",
            PokemonLocationArea::class.java to "pokemon-location-area",
            PokemonColor::class.java to "pokemon-color",
            PokemonForm::class.java to "pokemon-form",
            PokemonHabitat::class.java to "pokemon-habitat",
            PokemonShape::class.java to "pokemon-shape",
            PokemonSpecies::class.java to "pokemon-species",
            Stat::class.java to "stat",
            Type::class.java to "type"
        )

    @Test
    fun `resolves every root entity to the path the old when returned`() {
        expected.forEach { (type, path) ->
            assertEquals(path, EndpointResolver.resolve(type), "wrong path for ${type.simpleName}")
        }
    }

    @Test
    fun `covers the full endpoint table`() {
        assertEquals(48, expected.size)
    }

    @Test
    fun `throws for a type carrying no Endpoint annotation`() {
        val error =
            assertThrows<UnknownEndpointException> {
                EndpointResolver.resolve(String::class.java)
            }
        assertTrue(error.message!!.contains("String"))
    }

    @Test
    fun `caches a resolved path so a second resolution does not grow the cache`() {
        EndpointResolver.paths.remove(Berry::class.java)
        assertFalse(EndpointResolver.paths.containsKey(Berry::class.java))

        val resolved = EndpointResolver.resolve(Berry::class.java)

        assertEquals("berry", resolved)
        assertEquals("berry", EndpointResolver.paths[Berry::class.java])
        val sizeAfterFirstResolve = EndpointResolver.paths.size

        EndpointResolver.resolve(Berry::class.java)

        assertEquals(sizeAfterFirstResolve, EndpointResolver.paths.size)
    }
}
