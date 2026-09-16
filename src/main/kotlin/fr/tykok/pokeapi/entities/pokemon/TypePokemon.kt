package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource

data class TypePokemon(
    val slot: Int,
    val pokemon: NamedApiResource<Pokemon>
) : PokeApiObject
