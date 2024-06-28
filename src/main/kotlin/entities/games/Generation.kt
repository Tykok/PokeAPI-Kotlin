package entities.games

import com.fasterxml.jackson.annotation.JsonProperty
import entities.PokeApiObject
import entities.common.Name
import entities.common.NamedApiResource
import entities.locations.Region
import entities.moves.Move
import entities.pokemon.Ability
import entities.pokemon.PokemonSpecies
import entities.pokemon.Type

/**
 * A generation is a grouping of the Pokémon games that separates them based on the Pokémon they include.
 * In each generation, a new set of Pokémon, Moves, Abilities and Types that did not exist in the previous generation are released.
 *
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Generation">Bulbapedia documentation</a>
 * @see <a href="https://pokeapi.co/docs/v2#generations">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class Generation(
    @JsonProperty("id")
    val id: Number,
    @JsonProperty("name")
    val name: String,
    /**
     * @see NamedApiResource
     * @see Ability
     */
    @JsonProperty("abilities")
    val abilities: List<NamedApiResource<Ability>>,
    @JsonProperty("names")
    val names: List<Name>,
    /**
     * @see NamedApiResource
     * @see Region
     */
    @JsonProperty("main_region")
    val mainRegion: NamedApiResource<Region>,
    /**
     * @see NamedApiResource
     * @see Move
     */
    @JsonProperty("moves")
    val moves: List<NamedApiResource<Move>>,
    /**
     * @see NamedApiResource
     * @see PokemonSpecies
     */
    @JsonProperty("pokemon_species")
    val pokemonSpecies: List<NamedApiResource<PokemonSpecies>>,
    /**
     * @see NamedApiResource
     * @see Type
     */
    @JsonProperty("types")
    val types: List<NamedApiResource<Type>>,
    /**
     * @see NamedApiResource
     * @see VersionGroup
     */
    @JsonProperty("version_groups")
    val versionGroups: List<NamedApiResource<VersionGroup>>
) : PokeApiObject
