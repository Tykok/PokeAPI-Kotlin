package fr.tykok.pokeapi.entities.locations

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.pokemon.PokemonSpecies

/**
 * @see <a href="https://pokeapi.co/docs/v2#palparkencounterspecies">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class PalParkEncounterSpecies(
    /**
     * The base score given to the player when this Pokémon is caught during a pal park run
     */
    @JsonProperty("base_score")
    val baseScore: Number,
    /**
     * The base rate for encountering this Pokémon in this pal park area
     */
    @JsonProperty("rate")
    val rate: Number,
    /**
     * The Pokémon species being encountered
     * @see NamedApiResource
     * @ee PokemonSpecies
     */
    @JsonProperty("pokemon_species")
    val pokemonSpecies: NamedApiResource<PokemonSpecies>
)
