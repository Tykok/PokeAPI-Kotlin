package entity.berries

import entity.NamedApiResource

class FlavorBerryMap(
    /**
     * How powerful the referenced flavor is for this berry.
     */
    val potency: Number,

    /**
     * The berry with the referenced flavor.
     */
    val berry: NamedApiResource,
)