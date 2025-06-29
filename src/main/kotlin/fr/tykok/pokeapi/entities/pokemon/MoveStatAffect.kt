package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.moves.Move

/**
 * @property change The maximum amount of change to the referenced stat.
 * @property move The move causing the change.
 */
data class MoveStatAffect(
    val change: Number,
    val move: NamedApiResource<Move>
) : PokeApiObject
