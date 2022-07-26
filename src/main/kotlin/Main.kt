import com.fasterxml.jackson.databind.ObjectMapper
import entity.berries.Berry
import entity.berries.BerryFirmness
import entity.berries.BerryFlavor
import entity.contests.ContestEffect
import entity.contests.ContestType
import entity.contests.SuperContestEffects
import entity.encounters.EncounterCondition
import entity.encounters.EncounterConditionValue
import entity.encounters.EncounterMethod
import entity.evolution.EvolutionChain
import entity.evolution.EvolutionTrigger
import entity.games.Generation
import entity.games.Pokedex
import entity.games.Version
import entity.games.VersionGroup
import entity.items.*
import entity.locations.Location
import entity.locations.LocationArea
import entity.locations.PalParkArea
import entity.locations.Region
import entity.machines.Machine
import entity.moves.*
import entity.pokemon.*
import http.makeRequest

fun main(args: Array<String>) {
    val pokemonResult = makeRequest("https://pokeapi.co/api/v2/type/1")
    val objectMapper = ObjectMapper()
    val pokemon: Type = objectMapper.readValue(pokemonResult, Type::class.java)
    print(pokemon)
}
