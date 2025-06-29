package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource

data class TypePokemon(
    val slot: Number,
    val pokemon: NamedApiResource<Pokemon>
) : PokeApiObject
