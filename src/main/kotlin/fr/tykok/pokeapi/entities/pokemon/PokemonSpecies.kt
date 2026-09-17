package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.annotation.Endpoint
import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.APIResource
import fr.tykok.pokeapi.entities.common.Description
import fr.tykok.pokeapi.entities.common.FlavorText
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.games.Generation

/**
 * Represents a Pokémon species.
 */
@Endpoint("pokemon-species")
public data class PokemonSpecies(
    val id: Int,
    val name: String,
    val order: Int,
    val genderRate: Int,
    val captureRate: Int,
    val baseHappiness: Int,
    val isBaby: Boolean,
    val isLegendary: Boolean,
    val isMythical: Boolean,
    val hatchCounter: Int,
    val hasGenderDifferences: Boolean,
    val formsSwitchable: Boolean,
    val growthRate: NamedApiResource<GrowthRate>,
    val pokedexNumbers: List<PokemonSpeciesDexEntry>,
    val eggGroups: List<NamedApiResource<EggGroup>>,
    val color: NamedApiResource<PokemonColor>,
    val shape: NamedApiResource<PokemonShape>,
    val evolvesFromSpecies: NamedApiResource<PokemonSpecies>?,
    val evolutionChain: APIResource,
    val habitat: NamedApiResource<PokemonHabitat>?,
    val generation: NamedApiResource<Generation>,
    val names: List<Name>,
    val palParkEncounters: List<PalParkEncounterArea>,
    val flavorTextEntries: List<FlavorText>,
    val formDescriptions: List<Description>,
    val genera: List<Genus>,
    val varieties: List<PokemonSpeciesVariety>
) : PokeApiEndpointReference
