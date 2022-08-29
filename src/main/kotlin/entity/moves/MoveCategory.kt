package entity.moves

import entity.common.Description
import entity.common.NamedApiResource

class MoveCategory(

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
    val moves: Array<NamedApiResource>,


    /**
     * The description of this resource listed in different languages.
     * @see Move
     */
    val descriptions: Array<Description>
)