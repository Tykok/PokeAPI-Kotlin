package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.games.Generation

data class PokemonTypePast(
    /**
     * The last generation in which the referenced pokémon had the listed types.
     * @see Generation
     */
    val generation: NamedApiResource<Generation>,
    /**
     * The types the referenced pokémon had in the listed generation.
     */
    val types: List<PokemonType>
) : PokeApiObject
