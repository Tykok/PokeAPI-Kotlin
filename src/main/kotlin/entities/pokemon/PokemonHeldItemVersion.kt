package entities.pokemon

import entities.common.NamedApiResource
import entities.games.Version

data class PokemonHeldItemVersion(
    /**
     * The version in which the item is held.
     */
    val version: NamedApiResource<Version>,
    /**
     * How often the item is held.
     */
    val rarity: Number
)
