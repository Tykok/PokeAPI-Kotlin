package entity.berries

import entity.Name
import entity.NamedApiResource

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
    val id: Number,

    /**
     * The name of the BerryFlavor
     */
    val name: String,

    /**
     * A list of the berries with this flavor.
     * @see FlavorBerryMap
     */
    val berries: Array<FlavorBerryMap>,

    /**
     * The contest type that correlates with this berry flavor.
     * @see NamedApiResource
     */
    val contest_type: NamedApiResource,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    val names: Array<Name>
)