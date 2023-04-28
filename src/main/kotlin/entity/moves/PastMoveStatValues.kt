package entity.moves

import com.fasterxml.jackson.annotation.JsonProperty
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
    @JsonProperty("accuracy")
    val accuracy: Number,

    /**
     * The percent value of how likely it is this moves effect will take effect.
     */
    @JsonProperty("effect_chance")
    val effectChance: Number,

    /**
     * The base power of this move with a value of 0 if it does not have a base power.
     */
    @JsonProperty("power")
    val power: Number,

    /**
     * Power points. The number of times this move can be used.
     */
    @JsonProperty("pp")
    val pp: Number,

    /**
     * The effect of this move listed in different languages.
     * @see VerboseEffect
     */
    @JsonProperty("effect_entries")
    val effectEntries: List<VerboseEffect>,

    /**
     * The elemental type of this move.
     * @see NamedApiResource
     * @see Type
     */
    @JsonProperty("type")
    val type: NamedApiResource,

    /**
     * The version group in which these move stat values were in effect.
     * @see NamedApiResource
     * @see VersionGroup
     */
    @JsonProperty("version_group")
    val versionGroup: NamedApiResource
) {
    override fun toString(): String {
        return "PastMoveStatValues(accuracy=$accuracy, effectChance=$effectChance, power=$power, pp=$pp, effectEntries=$effectEntries, type=$type, versionGroup=$versionGroup)"
    }
}
