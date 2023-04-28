package entity.berries

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.Name
import entity.common.NamedApiResource

/**
 * @link https://pokeapi.co/docs/v2#berry-firmnesses
 * @author Tykok
 * @version 1.0.0
 * @since 2022-07-27
 */
class BerryFirmness(

    @JsonProperty("id")
    val id: Number,

    @JsonProperty("name")
    val name: String,

    /**
     * A list of the berries with this firmness.
     * @see NamedApiResource
     */
    @JsonProperty("berries")
    val berries: Array<NamedApiResource>,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    @JsonProperty("names")
    val names: Array<Name>
) {
    override fun toString(): String {
        return "BerryFirmness(id=$id, name='$name', berries=${berries.contentToString()}, names=${names.contentToString()})"
    }
}
