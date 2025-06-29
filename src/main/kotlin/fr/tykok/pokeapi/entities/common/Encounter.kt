package fr.tykok.pokeapi.entities.common

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.encounters.EncounterConditionValue
import fr.tykok.pokeapi.entities.encounters.EncounterMethod

/**
 * @see <a href="https://pokeapi.co/docs/v2#encounter">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class Encounter(
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
    val conditionValues: List<NamedApiResource<EncounterConditionValue>>,
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
    val method: NamedApiResource<EncounterMethod>
) : PokeApiObject
