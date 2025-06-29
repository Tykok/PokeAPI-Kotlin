package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource

/**
 * Represents a shape that a Pokémon species can take on.
 *
 * @property id The identifier for this resource.
 * @property name The name for this resource.
 * @property awesomeNames The "scientific" name of this Pokémon shape listed in different languages.
 * @property names The name of this resource listed in different languages.
 * @property pokemonSpecies A list of the Pokémon species that have this shape.
 */
data class PokemonShape(
    val id: Number,
    val name: String,
    val awesomeNames: List<AwesomeName>,
    val names: List<Name>,
    val pokemonSpecies: List<NamedApiResource<PokemonSpecies>>
) : PokeApiEndpointReference
