package entities.pokemon

import entities.common.NamedApiResource
import entities.moves.Move

/**
 * @property change The maximum amount of change to the referenced stat.
 * @property move The move causing the change.
 */
data class MoveStatAffect(
    val change: Number,
    val move: NamedApiResource<Move>
)
