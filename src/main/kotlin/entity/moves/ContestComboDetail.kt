package entity.moves

import com.fasterxml.jackson.annotation.JsonProperty
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
    @JsonProperty("use_before")
    val useBefore: List<NamedApiResource>? = null,

    /**
     * A list of moves to use after this move.
     * @see NamedApiResource
     * @see Move
     */
    @JsonProperty("use_after")
    val useAfter: List<NamedApiResource>? = null

) {
    override fun toString(): String {
        return "ContestComboDetail(useBefore=$useBefore, useAfter=$useAfter)"
    }
}