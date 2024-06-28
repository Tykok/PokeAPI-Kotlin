package entities.moves

import entities.PokeApiObject
import entities.common.Description
import entities.common.NamedApiResource

/**
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-30
 *
 */
data class MoveCategory(
    /**
     * The identifier for this resource.
     */
    val id: Number,
    /**
     * The name for this resource.
     */
    val name: String,
    /**
     * A list of moves that fall into this category.
     * @see NamedApiResource
     * @see Move
     */
    val moves: Array<NamedApiResource<Move>>,
    /**
     * The description of this resource listed in different languages.
     * @see Move
     */
    val descriptions: Array<Description>
) : PokeApiObject
