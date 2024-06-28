package entities.pokemon

import entities.common.NamedApiResource
import entities.items.Item

data class PokemonHeldItem(
    /**
     * The item the referenced Pokémon holds.
     */
    val item: NamedApiResource<Item>,
    /**
     * The details for the version that this item is held in by the Pokémon.
     */
    val version_details: Array<PokemonHeldItemVersion>
)
