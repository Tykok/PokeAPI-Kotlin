package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.games.Version

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
