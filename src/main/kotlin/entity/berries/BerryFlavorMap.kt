package entity.berries

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource

/**
 * @link https://pokeapi.co/docs/v2#berries
 * @author Tykok
 * @version 1.0.0
 * @since 2022-07-27
 */
class BerryFlavorMap(

    /**
     * How powerful the referenced flavor is for this berry.
     */
    @JsonProperty("potency")
    val potency: String,

    /**
     * The referenced berry flavor.
     * With each reference you can have a ``BerryFlavor`` object.
     * @see NamedApiResource
     * @see BerryFlavor
     */
    @JsonProperty("flavor")
    val flavor: NamedApiResource
) {
    override fun toString(): String {
        return "BerryFlavorMap(potency=$potency, flavor=$flavor)"
    }
}
