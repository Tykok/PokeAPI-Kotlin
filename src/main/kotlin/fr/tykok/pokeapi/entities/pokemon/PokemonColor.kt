package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource

/**
 * A class representing the details of a Pokémon color.
 *
 * @property id The identifier for this resource.
 * @property name The name for this resource.
 * @property names The name of this resource listed in different languages.
 * @property pokemonSpecies A list of the Pokémon species that have this color.
 */
data class PokemonColor(
    val id: Int,
    val name: String,
    val names: List<Name>,
    val pokemonSpecies: List<NamedApiResource<PokemonSpecies>>
) : PokeApiEndpointReference
