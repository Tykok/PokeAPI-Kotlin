package entity.moves

import entity.common.NamedApiResource

/**
 * @see <a href="https://pokeapi.co/docs/v2#contestcombodetail">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class ContestComboDetail(

    /**
     * A list of moves to use before this move.
     * @see NamedApiResource
     * @see Move
     */
    use_before: Array<NamedApiResource>,

    /**
     * A list of moves to use after this move.
     * @see NamedApiResource
     * @see Move
     */
    use_after: Array<NamedApiResource>

)