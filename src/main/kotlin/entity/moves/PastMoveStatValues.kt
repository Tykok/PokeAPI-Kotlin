package entity.moves

import entity.common.NamedApiResource
import entity.common.VerboseEffect

/**
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class PastMoveStatValues(

    /**
     * The percent value of how likely this move is to be successful.
     */
    val accuracy: Number,

    /**
     * The percent value of how likely it is this moves effect will take effect.
     */
    val effect_chance: Number,

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
    val effect_entries: Array<VerboseEffect>,

    /**
     * The elemental type of this move.
     * @see NamedApiResource
     * @see Type
     */
    val type: NamedApiResource,

    /**
     * The version group in which these move stat values were in effect.
     * @see NamedApiResource
     * @see VersionGroup
     */
    val version_group: NamedApiResource
)