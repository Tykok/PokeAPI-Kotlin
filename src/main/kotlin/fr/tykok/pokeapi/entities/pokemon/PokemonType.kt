package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource

data class PokemonType(
    /**
     * The order the Pokémon's types are listed in.
     */
    val slot: Number,
    /**
     * The type the referenced Pokémon has.
     * @see Type
     */
    val type: NamedApiResource<Type>
) : PokeApiObject
