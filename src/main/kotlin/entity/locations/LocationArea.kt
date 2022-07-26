package entity.locations

import com.fasterxml.jackson.annotation.JsonProperty
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
    @JsonProperty("id")
    val id: Number,

    /**
     * The name for this resource.
     */
    @JsonProperty("name")
    val name: String,

    /**
     * The internal id of an API resource within game data.
     */
    @JsonProperty("game_index")
    val gameIndex: Number,

    /**
     * A list of methods in which Pokémon may be encountered in this area and how likely the method
     * will occur depending on the version of the game.
     * @see EncounterMethodRate
     */
    @JsonProperty("encounter_method_rates")
    val encounterMethodRates: List<EncounterMethodRate>,

    /**
     * The region this location area can be found in.
     * @see NamedApiResource
     * @see Location
     */
    @JsonProperty("location")
    val location: NamedApiResource,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    @JsonProperty("names")
    val names: List<Name>,

    /**
     * A list of Pokémon that can be encountered in this area along with version specific details about the encounter.
     * @see PokemonEncounter
     */
    @JsonProperty("pokemon_encounters")
    val pokemonEncounters: List<PokemonEncounter>
) {
    override fun toString(): String {
        return "LocationArea(id=$id, name=$name, gameIndex=$gameIndex, encounterMethodRates=$encounterMethodRates, location=$location, " +
            "names=$names, pokemonEncounters=$pokemonEncounters)"
    }
}
