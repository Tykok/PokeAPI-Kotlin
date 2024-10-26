package fr.tykok.pokeapi.entities.games

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.pokemon.PokemonSpecies

/**
 * @see <a href="https://pokeapi.co/docs/v2#pokedexes">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class PokemonEntry(
    /**
     * The index of this Pokémon species entry within the Pokédex.
     */
    @JsonProperty("entry_number")
    val entryNumber: Number,
    /**
     * The Pokémon species being encountered.
     * @see NamedApiResource
     * @see PokemonSpecies
     */
    @JsonProperty("pokemon_species")
    val pokemonSpecies: NamedApiResource<PokemonSpecies>
)
