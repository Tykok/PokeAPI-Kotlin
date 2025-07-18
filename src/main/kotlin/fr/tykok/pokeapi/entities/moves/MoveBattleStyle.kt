package fr.tykok.pokeapi.entities.moves

import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.Name

/**
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-30
 *
 */
data class MoveBattleStyle(
    /**
     * The identifier for this resource.
     */
    val id: Number,
    /**
     * The name for this resource.
     */
    val name: String,
    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    val names: List<Name>
) : PokeApiEndpointReference
