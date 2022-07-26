package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.Description
import entity.common.NamedApiResource

class GrowthRate(

    /**
     * The identifier for this resource.
     */
    @JsonProperty("id")
    val id: Number,

    /**
     * The name for this resource.
     */
    @JsonProperty("name")
    val name: String,

    /**
     * The formula used to calculate the rate at which the Pokémon species gains level.
     */
    @JsonProperty("formula")
    val formula: String,

    /**
     * The descriptions of this characteristic listed in different languages.
     */
    @JsonProperty("descriptions")
    val descriptions: List<Description>,

    /**
     * A list of levels and the amount of experienced needed to atain them based on this growth rate.
     */
    @JsonProperty("levels")
    val levels: List<GrowthRateExperienceLevel>,

    /**
     * A list of Pokémon species that gain levels at this growth rate.
     *  @see PokemonSpecies
     */
    @JsonProperty("pokemon_species")
    val pokemonSpecies: List<NamedApiResource>
) {
    override fun toString(): String {
        return "GrowthRate(id=$id, name='$name', formula='$formula', descriptions=$descriptions, levels=$levels, pokemonSpecies=$pokemonSpecies)"
    }
}