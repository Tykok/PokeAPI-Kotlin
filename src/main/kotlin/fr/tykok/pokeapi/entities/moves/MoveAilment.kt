package fr.tykok.pokeapi.entities.moves

import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource

/**
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-30
 *
 */
data class MoveAilment(
    /**
     * The identifier for this resource.
     */
    val id: Number,
    /**
     * The name for this resource.
     */
    val name: String,
    /**
     * A list of moves that cause this ailment.
     * @see NamedApiResource
     * @see Move
     */
    val moves: List<NamedApiResource<Move>>,
    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    val names: List<Name>
) : PokeApiEndpointReference
