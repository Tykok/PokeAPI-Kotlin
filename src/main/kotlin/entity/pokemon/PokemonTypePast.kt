package entity.pokemon

import entity.common.NamedApiResource

class PokemonTypePast(
    /**
     * The last generation in which the referenced pokémon had the listed types.
     * @see Generation
     */
    val generation: NamedApiResource,

    /**
     * The types the referenced pokémon had in the listed generation.
     */
    val types: Array<PokemonType>
)
