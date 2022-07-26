package entity.berries

import entity.Name
import entity.NamedApiResource

class BerryFlavor(
    val id: Number,
    val name: String,

    /**
     * A list of the berries with this flavor.
     */
    val berries: Array<FlavorBerryMap>,

    /**
     * The contest type that correlates with this berry flavor.
     */
    val contest_type: NamedApiResource,

    /**
     * The name of this resource listed in different languages.
     */
    val names: Array<Name>
)