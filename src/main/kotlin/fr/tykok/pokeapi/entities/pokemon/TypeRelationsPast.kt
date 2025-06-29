package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource

data class TypeRelationsPast(
    /**
     * A list of details of how effective this type was toward others and vice versa in previous generations
     * @see NamedApiResource
     * @see Type
     */
    val generation: NamedApiResource<Type>,
    val damageRelations: TypeRelations
) : PokeApiObject
