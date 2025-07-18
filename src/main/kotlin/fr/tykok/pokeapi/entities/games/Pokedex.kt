package fr.tykok.pokeapi.entities.games
import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.Description
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.locations.Region

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
data class Pokedex(
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
    val isMainSeries: Boolean,
    /**
     * The description of this resource listed in different languages.
     * @see Description
     */
    val descriptions: List<Description>,
    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    val names: List<Name>,
    /**
     * A list of Pokémon catalogued in this Pokédex and their indexes.
     * @see PokemonEntry
     */
    val pokemonEntries: List<PokemonEntry>,
    /**
     * The region this Pokédex catalogues Pokémon for.
     * @see NamedApiResource
     * @see Region
     */
    val region: NamedApiResource<Region>?,
    /**
     * A list of version groups this Pokédex is relevant to.
     * @see NamedApiResource
     * @see VersionGroup
     */
    val versionGroups: List<NamedApiResource<VersionGroup>>?
) : PokeApiEndpointReference
