package entity.games

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource
import entity.locations.Region
import entity.moves.MoveLearnMethod

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
    @JsonProperty("id")
    val id: Number,

    /**
     * The name for this resource.
     */
    @JsonProperty("name")
    val name: String,

    /**
     * Order for sorting. Almost by date of release, except similar versions are grouped together.
     */
    @JsonProperty("order")
    val order: Number,

    /**
     * The generation this version was introduced in.
     * @see NamedApiResource
     * @see Generation
     */
    @JsonProperty("generation")
    val generation: NamedApiResource<Generation>,

    /**
     * A list of methods in which Pokémon can learn moves in this version group.
     * @see NamedApiResource
     * @see MoveLearnMethod
     */
    @JsonProperty("move_learn_methods")
    val moveLearnMethods: List<NamedApiResource<MoveLearnMethod>>,

    /**
     * A list of Pokédexes introduces in this version group.
     * @see NamedApiResource
     * @see Pokedex
     */
    @JsonProperty("pokedexes")
    val pokedexes: List<NamedApiResource<Pokedex>>,

    /**
     * A list of regions that can be visited in this version group.
     * @see NamedApiResource
     * @see Region
     */
    @JsonProperty("regions")
    val regions: List<NamedApiResource<Region>>,

    /**
     * The versions this version group owns.
     * @see NamedApiResource
     * @see Version
     */
    @JsonProperty("versions")
    val versions: List<NamedApiResource<Version>>
) {
    override fun toString(): String {
        return "VersionGroup(id=$id, name='$name', order=$order, generation=$generation, moveLearnMethods=$moveLearnMethods, pokedexes=$pokedexes, regions=$regions, versions=$versions)"
    }
}
