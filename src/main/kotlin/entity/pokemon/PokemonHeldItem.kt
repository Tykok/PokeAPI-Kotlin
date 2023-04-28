package entity.pokemon

import entity.common.NamedApiResource

class PokemonHeldItem(
    /**
     * The item the referenced Pokémon holds.
     */
    val item: NamedApiResource,

    /**
     * The details for the version that this item is held in by the Pokémon.
     */
    val version_details: Array<PokemonHeldItemVersion>
)
