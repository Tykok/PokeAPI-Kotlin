package fr.tykok.pokeapi.entities.moves

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.pokemon.Stat

/**
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class MoveStatChange(
    /**
     * The amount of change.
     */
    @JsonProperty("change")
    val change: Number,
    /**
     * The stat being affected.
     * @see NamedApiResource
     * @see Stat
     */
    @JsonProperty("stat")
    val stat: NamedApiResource<Stat>
)
