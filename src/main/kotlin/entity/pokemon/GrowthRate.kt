package entity.pokemon

import entity.common.Description
import entity.common.NamedApiResource

class GrowthRate(

    /**
     * The identifier for this resource.
     */
    val id: Number,

    /**
     * The name for this resource.
     */
    val name: String,

    /**
     * The formula used to calculate the rate at which the Pokémon species gains level.
     */
    val formula: String,

    /**
     * The descriptions of this characteristic listed in different languages.
     */
    val descriptions: Array<Description>,

    /**
     * A list of levels and the amount of experienced needed to atain them based on this growth rate.
     */
    val levels: Array<GrowthRateExperienceLevel>,

    /**
     * A list of Pokémon species that gain levels at this growth rate.
     *  @see PokemonSpecies
     */
    val pokemon_species: Array<NamedApiResource>
)