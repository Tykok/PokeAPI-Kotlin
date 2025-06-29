package fr.tykok.pokeapi.entities.moves

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.common.VerboseEffect
import fr.tykok.pokeapi.entities.games.VersionGroup
import fr.tykok.pokeapi.entities.pokemon.Type

/**
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class PastMoveStatValues(
    /**
     * The percent value of how likely this move is to be successful.
     */
    val accuracy: Number,
    /**
     * The percent value of how likely it is this moves effect will take effect.
     */
    val effectChance: Number,
    /**
     * The base power of this move with a value of 0 if it does not have a base power.
     */
    val power: Number,
    /**
     * Power points. The number of times this move can be used.
     */
    val pp: Number,
    /**
     * The effect of this move listed in different languages.
     * @see VerboseEffect
     */
    val effectEntries: List<VerboseEffect>,
    /**
     * The elemental type of this move.
     * @see NamedApiResource
     * @see Type
     */
    val type: NamedApiResource<Type>,
    /**
     * The version group in which these move stat values were in effect.
     * @see NamedApiResource
     * @see VersionGroup
     */
    val versionGroup: NamedApiResource<VersionGroup>
) : PokeApiObject
