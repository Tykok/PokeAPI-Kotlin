package entity.locations

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
    val id: Number,

    /**
     * A list of locations that can be found in this region.
     * @see NamedApiResource
     * @see Location
     */
    val locations: Array<NamedApiResource>,

    /**
     * The name for this resource.
     */
    val name: String,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    val names: Array<Name>,

    /**
     * The generation this region was introduced in.
     * @see NamedApiResource
     * @see Generation
     */
    val main_generation: NamedApiResource,

    /**
     * A list of pokédexes that catalogue Pokémon in this region.
     * @see Pokedex
     * @see NamedApiResource
     */
    val pokedexes: Array<NamedApiResource>,

    /**
     * A list of version groups where this region can be visited.
     * @see Pokedex
     * @see VersionGroup
     */
    val version_groups: Array<NamedApiResource>
)