package fr.tykok.pokeapi.entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.APIResource
import fr.tykok.pokeapi.entities.common.Description
import fr.tykok.pokeapi.entities.common.FlavorText
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.games.Generation

/**
 * Represents a Pokémon species.
 */
data class PokemonSpecies(
    @JsonProperty("id")
    val id: Number,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("order")
    val order: Number,
    @JsonProperty("gender_rate")
    val genderRate: Number,
    @JsonProperty("capture_rate")
    val captureRate: Number,
    @JsonProperty("base_happiness")
    val baseHappiness: Number,
    @JsonProperty("is_baby")
    val isBaby: Boolean,
    @JsonProperty("is_legendary")
    val isLegendary: Boolean,
    @JsonProperty("is_mythical")
    val isMythical: Boolean,
    @JsonProperty("hatch_counter")
    val hatchCounter: Number,
    @JsonProperty("has_gender_differences")
    val hasGenderDifferences: Boolean,
    @JsonProperty("forms_switchable")
    val formsSwitchable: Boolean,
    @JsonProperty("growth_rate")
    val growthRate: NamedApiResource<GrowthRate>,
    @JsonProperty("pokedex_numbers")
    val pokedexNumbers: List<PokemonSpeciesDexEntry>,
    @JsonProperty("egg_groups")
    val eggGroups: List<NamedApiResource<EggGroup>>,
    @JsonProperty("color")
    val color: NamedApiResource<PokemonColor>,
    @JsonProperty("shape")
    val shape: NamedApiResource<PokemonShape>,
    @JsonProperty("evolves_from_species")
    val evolvesFromSpecies: NamedApiResource<PokemonSpecies>?,
    @JsonProperty("evolution_chain")
    val evolutionChain: APIResource,
    @JsonProperty("habitat")
    val habitat: NamedApiResource<PokemonHabitat>?,
    @JsonProperty("generation")
    val generation: NamedApiResource<Generation>,
    @JsonProperty("names")
    val names: List<Name>,
    @JsonProperty("pal_park_encounters")
    val palParkEncounters: List<PalParkEncounterArea>,
    @JsonProperty("flavor_text_entries")
    val flavorTextEntries: List<FlavorText>,
    @JsonProperty("form_descriptions")
    val formDescriptions: List<Description>,
    @JsonProperty("genera")
    val genera: List<Genus>,
    @JsonProperty("varieties")
    val varieties: List<PokemonSpeciesVariety>
) : PokeApiObject
