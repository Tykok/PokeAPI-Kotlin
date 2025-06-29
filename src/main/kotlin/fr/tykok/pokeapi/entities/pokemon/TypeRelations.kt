package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource

data class TypeRelations(
    val noDamageTo: List<NamedApiResource<Type>>,
    val halfDamageTo: List<NamedApiResource<Type>>,
    val doubleDamageTo: List<NamedApiResource<Type>>,
    val noDamageFrom: List<NamedApiResource<Type>>,
    val halfDamageFrom: List<NamedApiResource<Type>>,
    val doubleDamageFrom: List<NamedApiResource<Type>>
) : PokeApiObject
