package fr.tykok.pokeapi.entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.PokeApiObject
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
    @JsonProperty("id")
    val id: Number,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("awesome_names")
    val awesomeNames: List<AwesomeName>,
    @JsonProperty("names")
    val names: List<Name>,
    @JsonProperty("pokemon_species")
    val pokemonSpecies: List<NamedApiResource<PokemonSpecies>>
) : PokeApiObject
