package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.Effect
import entity.common.NamedApiResource
import entity.games.Generation

class SuperContestEffects(
    /**
     * The previous effect of this ability listed in different languages.
     * @see Effect
     */
    @JsonProperty("effect_entries")
    val effectEntries: Array<Effect>,

    /**
     * The version group in which the previous effect of this ability originated.
     * @see NamedApiResource
     * @see Generation
     */
    @JsonProperty("version_group")
    val versionGroup: NamedApiResource
) {
    override fun toString(): String {
        return "AbilityEffectChange(effectEntries=${effectEntries.contentToString()}, versionGroup=$versionGroup)"
    }
}
