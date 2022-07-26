package entity.moves

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource

/**
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class MoveStatChange(
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
    val stat: NamedApiResource
) {
    override fun toString(): String {
        return "MoveStatChange(change=$change, stat=$stat)"
    }
}