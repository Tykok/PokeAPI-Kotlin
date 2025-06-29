package fr.tykok.pokeapi.entities.moves

import fr.tykok.pokeapi.entities.PokeApiObject
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
    val change: Number,
    /**
     * The stat being affected.
     * @see NamedApiResource
     * @see Stat
     */
    val stat: NamedApiResource<Stat>
) : PokeApiObject
