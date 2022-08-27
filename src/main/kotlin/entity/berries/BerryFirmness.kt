package entity.berries

import entity.common.Name
import entity.common.NamedApiResource

/**
 * @link https://pokeapi.co/docs/v2#berry-firmnesses
 * @author Tykok
 * @version 1.0.0
 * @since 2022-07-27
 */
class BerryFirmness(
    val id: Number,
    val name: String,

    /**
     * A list of the berries with this firmness.
     * @see NamedApiResource
     */
    val berries: Array<NamedApiResource>,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    val names: Array<Name>
)