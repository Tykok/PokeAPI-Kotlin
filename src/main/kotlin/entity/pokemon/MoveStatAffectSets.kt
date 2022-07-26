package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @property increase A list of moves and how they change the referenced stat.
 * @property decrease A list of moves and how they change the referenced stat.
 */
class MoveStatAffectSets(

    @JsonProperty("increase")
    val increase: List<MoveStatAffect>,

    @JsonProperty("decrease")
    val decrease: List<MoveStatAffect>
) {

    override fun toString(): String {
        return "MoveStatAffectSets(increase=$increase, decrease=$decrease)"
    }
}
