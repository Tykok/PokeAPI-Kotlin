package fr.tykok.pokeapi.entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @property increase A list of moves and how they change the referenced stat.
 * @property decrease A list of moves and how they change the referenced stat.
 */
data class MoveStatAffectSets(
    @JsonProperty("increase")
    val increase: List<MoveStatAffect>,
    @JsonProperty("decrease")
    val decrease: List<MoveStatAffect>
)
