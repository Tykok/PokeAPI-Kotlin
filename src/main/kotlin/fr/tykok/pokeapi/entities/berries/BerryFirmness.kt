package fr.tykok.pokeapi.entities.berries

import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource

/**
 * @link https://pokeapi.co/docs/v2#berry-firmnesses
 * @author Tykok
 * @version 1.0.0
 * @since 2022-07-27
 */
data class BerryFirmness(

    val id: Number,

    val name: String,
    /**
     * A list of the berries with this firmness.
     * @see NamedApiResource
     * @see Berry
     */

    val berries: List<NamedApiResource<Berry>>,
    /**
     * The name of this resource listed in different languages.
     * @see Name
     */

    val names: List<Name>
) : PokeApiEndpointReference
