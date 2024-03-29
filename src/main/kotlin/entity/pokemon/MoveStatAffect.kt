package entity.pokemon

import entity.common.NamedApiResource
import entity.moves.Move

/**
 * @property change The maximum amount of change to the referenced stat.
 * @property move The move causing the change.
 */
class MoveStatAffect(
    val change: Number,
    val move: NamedApiResource<Move>
)
