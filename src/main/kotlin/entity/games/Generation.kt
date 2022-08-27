package entity.games

import entity.Name
import entity.NamedApiResource
import entity.pokemon.Ability
import entity.pokemon.PokemonSpecies
import entity.pokemon.Type
import entity.locations.Region
import entity.moves.Move

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
    val id: Number,
    val name: String,

    /**
     * @see NamedApiResource
     * @see Ability
     */
    val abilities: Array<NamedApiResource>,

    val names: Array<Name>,

    /**
     * @see NamedApiResource
     * @see Region
     */
    val main_region: NamedApiResource,


    /**
     * @see NamedApiResource
     * @see Move
     */
    val moves: NamedApiResource,

    /**
     * @see NamedApiResource
     * @see PokemonSpecies
     */
    val pokemon_species: NamedApiResource,

    /**
     * @see NamedApiResource
     * @see Type
     */
    val types: NamedApiResource,

    /**
     * @see NamedApiResource
     * @see VersionGroup
     */
    val version_groups: NamedApiResource
)