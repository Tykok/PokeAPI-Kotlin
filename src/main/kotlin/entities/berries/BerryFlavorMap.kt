package entities.berries

import com.fasterxml.jackson.annotation.JsonProperty
import entities.common.NamedApiResource

/**
 * @link https://pokeapi.co/docs/v2#berries
 * @author Tykok
 * @version 1.0.0
 * @since 2022-07-27
 */
data class BerryFlavorMap(
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
    val flavor: NamedApiResource<BerryFlavor>
)
