package entity.moves

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.Description
import entity.common.Name
import entity.common.NamedApiResource

/**
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-30
 *
 */
class MoveTarget(

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
     * The description of this resource listed in different languages.
     * @see Description
     */
    @JsonProperty("descriptions")
    val descriptions: List<Description>,

    /**
     * A list of moves that that are directed at this target.
     * @see NamedApiResource
     * @see Move
     */
    @JsonProperty("moves")
    val moves: List<NamedApiResource>,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    @JsonProperty("names")
    val names: List<Name>

) {
    override fun toString(): String {
        return "MoveTarget(id=$id, name='$name', descriptions=$descriptions, moves=$moves, names=$names)"
    }
}