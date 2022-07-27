package entity.berries

import entity.NamedApiResource

/**
 * @link https://pokeapi.co/docs/v2#berry-flavors
 * @author Tykok
 * @version 1.0.0
 * @since 2022-07-27
 */
class FlavorBerryMap(
    /**
     * How powerful the referenced flavor is for this berry.
     */
    val potency: Number,

    /**
     * The berry with the referenced flavor.
     * @see NamedApiResource
     * @see Berry References to get ``Berry``
     */
    val berry: NamedApiResource,
)