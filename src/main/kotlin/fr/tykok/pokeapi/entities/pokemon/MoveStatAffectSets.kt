package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiObject

/**
 * @property increase A list of moves and how they change the referenced stat.
 * @property decrease A list of moves and how they change the referenced stat.
 */
data class MoveStatAffectSets(
    val increase: List<MoveStatAffect>,
    val decrease: List<MoveStatAffect>
) : PokeApiObject
