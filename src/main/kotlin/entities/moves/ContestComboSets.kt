package entities.moves

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @see <a href="https://pokeapi.co/docs/v2#contestcombosets">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class ContestComboSets(
    /**
     * A detail of moves this move can be used before or after, granting additional appeal points in contests.
     * @see ContestComboDetail
     */
    @JsonProperty("normal")
    val normal: ContestComboDetail,
    /**
     * A detail of moves this move can be used before or after, granting additional appeal points in super contests.
     * @see ContestComboDetail
     */
    @JsonProperty("super")
    val `super`: ContestComboDetail
)
