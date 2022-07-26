package entity.berries

import entity.NamedApiResource

class Berry(
    val id: Number,
    val name: String,

    /**
     * Time it takes the tree to grow one stage, in hours.
     * Berry trees go through four of these growth stages before they can be picked.
     */
    val `growth_time`: Number,

    /**
     * The maximum number of these berries that can grow on one tree in Generation IV.
     */
    val max_harvest: Number,

    /**
     * The power of the move "Natural Gift" when used with this Berry.
     */
    val natural_gift_power: Number,

    /**
     * The size of this Berry, in millimeters.
     */
    val size: Number,

    /**
     * The smoothness of this Berry, used in making Pokéblocks or Poffins.
     */
    val smoothness: Number,

    /**
     * The speed at which this Berry dries out the soil as it grows.
     * A higher rate means the soil dries more quickly.
     */
    val soil_dryness: Number,

    /**
     * The firmness of this berry, used in making Pokéblocks or Poffins.
     */
    val firmness: NamedApiResource,

    /**
     * A list of references to each flavor a berry can have and the potency of each of those flavors in regard to this berry.
     */
    val flavors: Array<BerryFlavorMap>,

    /**
     * Berries are actually items. This is a reference to the item specific data for this berry.
     */
    val item: NamedApiResource,

    /**
     * The type inherited by "Natural Gift" when used with this Berry.
     */
    val natural_gift_type: NamedApiResource,
)