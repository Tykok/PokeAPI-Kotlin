package entity.berries

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.Name
import entity.common.NamedApiResource

/**
 * @link https://pokeapi.co/docs/v2#berry-flavors
 * @author Tykok
 * @version 1.0.0
 * @since 2022-07-27
 */
class BerryFlavor(

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
     */
    @JsonProperty("contest_type")
    val contestType: NamedApiResource,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    @JsonProperty("names")
    val names: Array<Name>
) {
    override fun toString(): String {
        return "BerryFlavor(id=$id, name='$name', berries=${berries.contentToString()}, contestType=$contestType, names=${names.contentToString()})"
    }
}
