package entities.pokemon

import entities.common.NamedApiResource
import entities.games.Generation

data class PokemonTypePast(
    /**
     * The last generation in which the referenced pokémon had the listed types.
     * @see Generation
     */
    val generation: NamedApiResource<Generation>,
    /**
     * The types the referenced pokémon had in the listed generation.
     */
    val types: Array<PokemonType>
)
