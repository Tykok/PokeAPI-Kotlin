package entity.locations

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.Name
import entity.common.NamedApiResource

/**
 * A region is an organized area of the Pokémon world. Most often, the main difference between regions is the
 * species of Pokémon that can be encountered within them.
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Region">Bulbapedia documentation</a>
 * @see <a href="https://pokeapi.co/docs/v2#regions">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class Region(

    /**
     * The identifier for this resource.
     */
    @JsonProperty("id")
    val id: Number,

    /**
     * A list of locations that can be found in this region.
     * @see NamedApiResource
     * @see Location
     */
    @JsonProperty("locations")
    val locations: List<NamedApiResource>,

    /**
     * The name for this resource.
     */
    @JsonProperty("name")
    val name: String,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    @JsonProperty("names")
    val names: List<Name>,

    /**
     * The generation this region was introduced in.
     * @see NamedApiResource
     * @see Generation
     */
    @JsonProperty("main_generation")
    val mainGeneration: NamedApiResource,

    /**
     * A list of pokédexes that catalogue Pokémon in this region.
     * @see Pokedex
     * @see NamedApiResource
     */
    @JsonProperty("pokedexes")
    val pokedexes: List<NamedApiResource>,

    /**
     * A list of version groups where this region can be visited.
     * @see Pokedex
     * @see VersionGroup
     */
    @JsonProperty("version_groups")
    val versionGroups: List<NamedApiResource>
) {
    override fun toString(): String {
        return "Region(id=$id, locations=$locations, name='$name', names=$names, mainGeneration=$mainGeneration, pokedexes=$pokedexes, versionGroups=$versionGroups)"
    }
}
