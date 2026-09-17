package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource

/**
 * @property isDefault Whether this variety is the default variety.
 * @property pokemon The Pokémon variety.
 */
public data class PokemonSpeciesVariety(
    val isDefault: Boolean,
    val pokemon: NamedApiResource<Pokemon>
) : PokeApiObject
