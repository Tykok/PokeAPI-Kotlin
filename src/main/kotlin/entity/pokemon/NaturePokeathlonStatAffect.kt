package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource

class NaturePokeathlonStatAffect(
    /**
     *  The maximum amount of change to the referenced Pokéathlon stat.
     */
    @JsonProperty("max_change")
    val maxChange: Number,

    /**
     * The nature causing the change.
     * @see Nature
     */
    @JsonProperty("nature")
    val nature: NamedApiResource
) {
    override fun toString(): String {
        return "NaturePokeathlonStatAffect(maxChange=$maxChange, nature=$nature)"
    }
}
