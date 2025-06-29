package fr.tykok.pokeapi.entities.berries

import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.contests.ContestType

/**
 * @link https://pokeapi.co/docs/v2#berry-flavors
 * @author Tykok
 * @version 1.0.0
 * @since 2022-07-27
 */
data class BerryFlavor(
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

    val berries: List<FlavorBerryMap>,
    /**
     * The contest type that correlates with this berry flavor.
     * @see NamedApiResource
     * @see ContestType
     */

    val contestType: NamedApiResource<ContestType>,
    /**
     * The name of this resource listed in different languages.
     * @see Name
     */

    val names: List<Name>
) : PokeApiEndpointReference
