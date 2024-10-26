package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.items.Item

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
