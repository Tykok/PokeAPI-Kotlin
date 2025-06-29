package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.games.Pokedex

/**
 * @property entryNumber The index number within the Pokédex.
 * @property pokedex The Pokédex the referenced Pokémon species can be found in.
 */
data class PokemonSpeciesDexEntry(
    val entryNumber: Number,
    val pokedex: NamedApiResource<Pokedex>
) : PokeApiObject
