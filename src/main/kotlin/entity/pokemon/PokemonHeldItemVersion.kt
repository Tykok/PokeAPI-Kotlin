package entity.pokemon

import entity.common.NamedApiResource

class PokemonHeldItemVersion(
    /**
     * The version in which the item is held.
     */
    val version: NamedApiResource,

    /**
     * How often the item is held.
     */
    val rarity: Number
)
