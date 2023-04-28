package entity.games

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.Description
import entity.common.Name
import entity.common.NamedApiResource
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
    @JsonProperty("id")
    val id: Number,

    /**
     * The name for this resource.
     */
    @JsonProperty("name")
    val name: String,

    /**
     * Whether or not this Pokédex originated in the main series of the video games.
     */
    @JsonProperty("is_main_series")
    val isMainSeries: Boolean,

    /**
     * The description of this resource listed in different languages.
     * @see Description
     */
    @JsonProperty("descriptions")
    val descriptions: List<Description>,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    @JsonProperty("names")
    val names: List<Name>,

    /**
     * A list of Pokémon catalogued in this Pokédex and their indexes.
     * @see PokemonEntry
     */
    @JsonProperty("pokemon_entries")
    val pokemonEntries: List<PokemonEntry>,

    /**
     * The region this Pokédex catalogues Pokémon for.
     * @see NamedApiResource
     * @see Region
     */
    @JsonProperty("region")
    val region: NamedApiResource?,

    /**
     * A list of version groups this Pokédex is relevant to.
     * @see NamedApiResource
     * @see VersionGroup
     */
    @JsonProperty("version_groups")
    val versionGroups: List<NamedApiResource>?
) {
    override fun toString(): String {
        return "Pokedex(id=$id, name='$name', isMainSeries=$isMainSeries, descriptions=$descriptions, names=$names, pokemonEntries=$pokemonEntries, region=$region, versionGroups=$versionGroups)"
    }
}
