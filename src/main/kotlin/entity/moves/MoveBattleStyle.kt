package entity.moves

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.Name

/**
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-30
 *
 */
class MoveBattleStyle(
    /**
     * The identifier for this resource.
     */
    @JsonProperty("id")
    val id: Number,

    /**
     * The name for this resource.
     */
    @JsonProperty("name")
    val name: String,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    @JsonProperty("names")
    val names: List<Name>

) {
    override fun toString(): String {
        return "MoveBattleStyle(id=$id, name='$name', names=$names)"
    }
}
