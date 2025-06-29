package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource

/**
 * @property increase A list of natures and how they change the referenced stat.
 * @property decrease A list of natures and how they change the referenced stat.
 */
data class NatureStatAffectSets(
    val increase: List<NamedApiResource<Nature>>,
    val decrease: List<NamedApiResource<Nature>>
) : PokeApiObject
