package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.*

/**
 * Represents a Pokémon species.
 */
class PokemonSpecies(

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
    val growthRate: NamedApiResource,

    @JsonProperty("pokedex_numbers")
    val pokedexNumbers: List<PokemonSpeciesDexEntry>,

    @JsonProperty("egg_groups")
    val eggGroups: List<NamedApiResource>,

    @JsonProperty("color")
    val color: NamedApiResource,

    @JsonProperty("shape")
    val shape: NamedApiResource,

    @JsonProperty("evolves_from_species")
    val evolvesFromSpecies: NamedApiResource?,

    @JsonProperty("evolution_chain")
    val evolutionChain: APIResource,

    @JsonProperty("habitat")
    val habitat: NamedApiResource?,

    @JsonProperty("generation")
    val generation: NamedApiResource,

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
) {
    override fun toString(): String {
        return "PokemonSpecies(id=$id, name='$name', order=$order, genderRate=$genderRate, captureRate=$captureRate, baseHappiness=$baseHappiness, " +
            "isBaby=$isBaby, isLegendary=$isLegendary, isMythical=$isMythical, hatchCounter=$hatchCounter, hasGenderDifferences=$hasGenderDifferences, " +
            "formsSwitchable=$formsSwitchable, growthRate=$growthRate, pokedexNumbers=$pokedexNumbers, eggGroups=$eggGroups, color=$color, " +
            "shape=$shape, evolvesFromSpecies=$evolvesFromSpecies, evolutionChain=$evolutionChain, habitat=$habitat, generation=$generation, " +
            "names=$names, palParkEncounters=$palParkEncounters, flavorTextEntries=$flavorTextEntries, formDescriptions=$formDescriptions, " +
            "genera=$genera, varieties=$varieties)"
    }
}
