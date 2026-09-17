package fr.tykok.pokeapi.entities.moves

import fr.tykok.pokeapi.annotation.Endpoint
import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.Name

/**
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-30
 *
 */
@Endpoint("move-battle-style")
public data class MoveBattleStyle(
    /**
     * The identifier for this resource.
     */
    val id: Int,
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
