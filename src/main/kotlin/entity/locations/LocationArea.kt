package entity.locations

import entity.common.Name
import entity.common.NamedApiResource

/**
 * Location areas are sections of areas, such as floors in a building or cave.
 * Each area has its own set of possible Pokémon encounters.
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/List_of_locations_by_name">Bulbapedia documentation</a>
 * @see <a href="https://pokeapi.co/docs/v2#location-areas">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class LocationArea(

    /**
     * The identifier for this resource.
     */
    val id: Number,

    /**
     * The name for this resource.
     */
    val name: String,

    /**
     * The internal id of an API resource within game data.
     */
    val game_index: Number,

    /**
     * A list of methods in which Pokémon may be encountered in this area and how likely the method
     * will occur depending on the version of the game.
     * @see EncounterMethodRate
     */
    val encounter_method_rates: Array<EncounterMethodRate>,

    /**
     * The region this location area can be found in.
     * @see NamedApiResource
     * @see Location
     */
    val location: NamedApiResource,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    val names: Array<Name>,
    
    /**
     * A list of Pokémon that can be encountered in this area along with version specific details about the encounter.
     * @see PokemonEncounter
     */
    val pokemon_encounters: Array<PokemonEncounter>
)