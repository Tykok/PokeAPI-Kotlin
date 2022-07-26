package entity.common

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @see <a href="https://pokeapi.co/docs/v2#encounter">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class Encounter(

    /**
     * The lowest level the Pokémon could be encountered at.
     */
    @JsonProperty("min_level")
    val minLevel: Number,

    /**
     * The highest level the Pokémon could be encountered at.
     */
    @JsonProperty("max_level")
    val maxLevel: Number,

    /**
     * A list of condition values that must be in effect for this encounter to occur.
     * @see EncounterConditionValue
     * @see NamedApiResource
     */
    @JsonProperty("condition_values")
    val conditionValues: List<NamedApiResource>,

    /**
     * Percent chance that this encounter will occur.
     */
    @JsonProperty("chance")
    val chance: Number,

    /**
     * The method by which this encounter happens.
     * @see EncounterMethod
     * @see NamedApiResource
     */
    @JsonProperty("method")
    val method: NamedApiResource

) {
    override fun toString(): String {
        return "Encounter(minLevel=$minLevel, maxLevel=$maxLevel, conditionValues=$conditionValues, chance=$chance, method=$method)"
    }
}