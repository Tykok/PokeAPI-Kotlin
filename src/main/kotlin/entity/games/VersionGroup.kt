package entity.games

import entity.NamedApiResource
import entity.moves.MoveLearnMethod
import entity.locations.Region

/**
 * Version groups categorize highly similar versions of the games.
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Core_series">Bulbapedia documentation</a>
 * @see <a href="https://pokeapi.co/docs/v2#version-groups">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class VersionGroup(

    /**
     * The identifier for this resource.
     */
    val id: Number,

    /**
     * The name for this resource.
     */
    val name: String,

    /**
     * Order for sorting. Almost by date of release, except similar versions are grouped together.
     */
    val order: Number,

    /**
     * The generation this version was introduced in.
     * @see NamedApiResource
     * @see Generation
     */
    val generation: NamedApiResource,

    /**
     * A list of methods in which Pokémon can learn moves in this version group.
     * @see NamedApiResource
     * @see MoveLearnMethod
     */
    val move_learn_methods: Array<NamedApiResource>,

    /**
     * A list of Pokédexes introduces in this version group.
     * @see NamedApiResource
     * @see Pokedex
     */
    val pokedexes: Array<NamedApiResource>,

    /**
     * A list of regions that can be visited in this version group.
     * @see NamedApiResource
     * @see Region
     */
    val regions: NamedApiResource,

    /**
     * The versions this version group owns.
     * @see NamedApiResource
     * @see Version
     */
    val versions: NamedApiResource
)