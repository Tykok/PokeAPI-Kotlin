package fr.tykok.pokeapi.entities.moves

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource

/**
 * @see <a href="https://pokeapi.co/docs/v2#contestcombodetail">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class ContestComboDetail(
    /**
     * A list of moves to use before this move.
     * @see NamedApiResource
     * @see Move
     */
    val useBefore: List<NamedApiResource<Move>>? = null,
    /**
     * A list of moves to use after this move.
     * @see NamedApiResource
     * @see Move
     */
    val useAfter: List<NamedApiResource<Move>>? = null
) : PokeApiObject
