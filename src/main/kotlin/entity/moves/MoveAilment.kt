package entity.moves

import entity.common.Name
import entity.common.NamedApiResource

class MoveAilment(

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
    val moves: Array<NamedApiResource>,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    val names: Array<Name>

)