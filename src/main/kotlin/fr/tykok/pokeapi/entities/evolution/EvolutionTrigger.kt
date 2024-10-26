package fr.tykok.pokeapi.entities.evolution

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.pokemon.PokemonSpecies

/**
 * Evolution triggers are the events and conditions that cause a Pokémon to evolve.
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Methods_of_evolution">Bulbapedia documentation</a>
 * @see <a href="https://pokeapi.co/docs/v2#evolution-triggers">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class EvolutionTrigger(
    /**
     * The identifier for this resource.
     */
    @JsonProperty("id")
    val id: Number,
    /**
     * The name for this resource.
     */
    @JsonProperty("name")
    val name: String,
    /**
     * The name of this resource listed in different languages.
     * @see NamedApiResource
     * @see Name
     */
    @JsonProperty("names")
    val names: Array<Name>,
    /**
     * A list of pokemon species that result from this evolution trigger.
     * @see NamedApiResource
     * @see PokemonSpecies
     */
    @JsonProperty("pokemon_species")
    val pokemonSpecies: Array<NamedApiResource<PokemonSpecies>>
) : PokeApiObject
