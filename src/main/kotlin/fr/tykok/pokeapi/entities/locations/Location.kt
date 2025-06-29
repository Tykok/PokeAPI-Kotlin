package fr.tykok.pokeapi.entities.locations
import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.GenerationGameIndex
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource

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
data class Location(
    /**
     * The identifier for this resource.
     */
    val id: Number,
    /**
     * The name for this resource.
     */
    val name: String,
    /**
     * The region this location can be found in.
     * @see NamedApiResource
     * @ee Region
     */
    val region: NamedApiResource<Region>,
    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    val names: List<Name>,
    /**
     * A list of game indices relevent to this location by generation.
     * @see GenerationGameIndex
     */
    val gameIndices: List<GenerationGameIndex>,
    /**
     * Areas that can be found within this location.
     * @see NamedApiResource
     * @see LocationArea
     */
    val areas: List<NamedApiResource<LocationArea>>
) : PokeApiEndpointReference
