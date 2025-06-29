package fr.tykok.pokeapi.entities.games
import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.locations.Region
import fr.tykok.pokeapi.entities.moves.MoveLearnMethod

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
data class VersionGroup(
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
    val generation: NamedApiResource<Generation>,
    /**
     * A list of methods in which Pokémon can learn moves in this version group.
     * @see NamedApiResource
     * @see MoveLearnMethod
     */
    val moveLearnMethods: List<NamedApiResource<MoveLearnMethod>>,
    /**
     * A list of Pokédexes introduces in this version group.
     * @see NamedApiResource
     * @see Pokedex
     */
    val pokedexes: List<NamedApiResource<Pokedex>>,
    /**
     * A list of regions that can be visited in this version group.
     * @see NamedApiResource
     * @see Region
     */
    val regions: List<NamedApiResource<Region>>,
    /**
     * The versions this version group owns.
     * @see NamedApiResource
     * @see Version
     */
    val versions: List<NamedApiResource<Version>>
) : PokeApiEndpointReference
