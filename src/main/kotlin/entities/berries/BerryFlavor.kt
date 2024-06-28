package entities.berries

import com.fasterxml.jackson.annotation.JsonProperty
import entities.PokeApiObject
import entities.common.Name
import entities.common.NamedApiResource
import entities.contests.ContestType

/**
 * @link https://pokeapi.co/docs/v2#berry-flavors
 * @author Tykok
 * @version 1.0.0
 * @since 2022-07-27
 */
data class BerryFlavor(
    /**
     * The identifier of the BerryFlavor
     */
    @JsonProperty("id")
    val id: Number,
    /**
     * The name of the BerryFlavor
     */
    @JsonProperty("name")
    val name: String,
    /**
     * A list of the berries with this flavor.
     * @see FlavorBerryMap
     */
    @JsonProperty("berries")
    val berries: Array<FlavorBerryMap>,
    /**
     * The contest type that correlates with this berry flavor.
     * @see NamedApiResource
     * @see ContestType
     */
    @JsonProperty("contest_type")
    val contestType: NamedApiResource<ContestType>,
    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    @JsonProperty("names")
    val names: Array<Name>
) : PokeApiObject
