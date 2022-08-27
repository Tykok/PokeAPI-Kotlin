package entity.games

import entity.Description
import entity.Name
import entity.NamedApiResource
import entity.locations.Region

/**
 * A Pokédex is a handheld electronic encyclopedia device; one which is capable of recording and retaining information
 * of the various Pokémon in a given region with the exception of the national dex and some smaller dexes related to
 * portions of a region.
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Pok%C3%A9dex">Bulbapedia documentation</a>
 * @see <a href="https://pokeapi.co/docs/v2#pokedexes">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class Pokedex(

    /**
     * The identifier for this resource.
     */
    val id: Number,

    /**
     * The name for this resource.
     */
    val name: String,

    /**
     * Whether or not this Pokédex originated in the main series of the video games.
     */
    val is_main_series: Boolean,

    /**
     * The description of this resource listed in different languages.
     * @see Description
     */
    val descriptions: Array<Description>,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    val names: Array<Name>,

    /**
     * A list of Pokémon catalogued in this Pokédex and their indexes.
     * @see PokemonEntry
     */
    val pokemon_entries: Array<PokemonEntry>,

    /**
     * The region this Pokédex catalogues Pokémon for.
     * @see NamedApiResource
     * @see Region
     */
    val region: NamedApiResource,

    /**
     * A list of version groups this Pokédex is relevant to.
     * @see NamedApiResource
     * @see VersionGroup
     */
    val version_groups: NamedApiResource
)