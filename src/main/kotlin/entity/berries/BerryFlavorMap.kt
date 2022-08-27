package entity.berries

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
    val potency: String,

    /**
     * The referenced berry flavor.
     * With each reference you can have a ``BerryFlavor`` object.
     * @see NamedApiResource
     * @see BerryFlavor
     */
    val flavor: NamedApiResource,
)