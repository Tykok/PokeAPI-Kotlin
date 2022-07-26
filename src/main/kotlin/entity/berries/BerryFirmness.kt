package entity.berries

import entity.Name
import entity.NamedApiResource

class BerryFirmness(
    val id: Number,
    val name: String,

    /**
     * A list of the berries with this firmness.
     */
    val berries: Array<NamedApiResource>,

    /**
     * The name of this resource listed in different languages.
     */
    val names: Array<Name>
)