package entity.games

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.Name
import entity.common.NamedApiResource
import entity.locations.Region
import entity.moves.Move
import entity.pokemon.Ability
import entity.pokemon.PokemonSpecies
import entity.pokemon.Type

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
class Generation(

    @JsonProperty("id")
    val id: Number,

    @JsonProperty("name")
    val name: String,

    /**
     * @see NamedApiResource
     * @see Ability
     */
    @JsonProperty("abilities")
    val abilities: List<NamedApiResource>,

    @JsonProperty("names")
    val names: List<Name>,

    /**
     * @see NamedApiResource
     * @see Region
     */
    @JsonProperty("main_region")
    val mainRegion: NamedApiResource,

    /**
     * @see NamedApiResource
     * @see Move
     */
    @JsonProperty("moves")
    val moves: List<NamedApiResource>,

    /**
     * @see NamedApiResource
     * @see PokemonSpecies
     */
    @JsonProperty("pokemon_species")
    val pokemonSpecies: List<NamedApiResource>,

    /**
     * @see NamedApiResource
     * @see Type
     */
    @JsonProperty("types")
    val types: List<NamedApiResource>,

    /**
     * @see NamedApiResource
     * @see VersionGroup
     */
    @JsonProperty("version_groups")
    val versionGroups: List<NamedApiResource>
) {
    override fun toString(): String {
        return "Generation(id=$id, name='$name', abilities=$abilities, names=$names, mainRegion=$mainRegion, moves=$moves, " +
            "pokemonSpecies=$pokemonSpecies, types=$types, versionGroups=$versionGroups)"
    }
}
