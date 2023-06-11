package entity.pokemon

import entity.common.NamedApiResource
import entity.games.Version

class PokemonHeldItemVersion(
    /**
     * The version in which the item is held.
     */
    val version: NamedApiResource<Version>,

    /**
     * How often the item is held.
     */
    val rarity: Number
)
