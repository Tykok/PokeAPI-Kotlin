package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.GenerationGameIndex
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.games.Generation
import fr.tykok.pokeapi.entities.moves.Move
import fr.tykok.pokeapi.entities.moves.MoveDamageClass

data class Type(
    val id: Int,
    val name: String,
    val damageRelations: TypeRelations,
    val pastDamageRelations: List<TypeRelationsPast>,
    val gameIndices: List<GenerationGameIndex>,
    val generation: NamedApiResource<Generation>,
    val moveDamageClass: NamedApiResource<MoveDamageClass>,
    val names: List<Name>,
    val pokemon: List<TypePokemon>,
    val moves: List<NamedApiResource<Move>>
) : PokeApiEndpointReference
