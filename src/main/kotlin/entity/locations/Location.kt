package entity.locations

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.GenerationGameIndex
import entity.common.Name
import entity.common.NamedApiResource

/**
 * Locations that can be visited within the games. Locations make up sizable portions of regions, like cities or routes.
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/List_of_locations_by_name">Bulbapedia documentation</a>
 * @see <a href="https://pokeapi.co/docs/v2#locations-section">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class Location(
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
     * The region this location can be found in.
     * @see NamedApiResource
     * @ee Region
     */
    @JsonProperty("region")
    val region: NamedApiResource<Region>,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    @JsonProperty("names")
    val names: List<Name>,

    /**
     * A list of game indices relevent to this location by generation.
     * @see GenerationGameIndex
     */
    @JsonProperty("game_indices")
    val gameIndices: List<GenerationGameIndex>,

    /**
     * Areas that can be found within this location.
     * @see NamedApiResource
     * @see LocationArea
     */
    @JsonProperty("areas")
    val areas: List<NamedApiResource<LocationArea>>
) {
    override fun toString(): String {
        return "Location(id=$id, name='$name', region=$region, names=$names, gameIndices=$gameIndices, areas=$areas)"
    }
}
