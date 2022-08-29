package entity.moves

import entity.common.Description
import entity.common.Name
import entity.common.NamedApiResource

class MoveDamageClass(

    /**
     * The identifier for this resource.
     */
    val id: Number,

    /**
     * The name for this resource.
     */
    val name: String,

    /**
     * The description of this resource listed in different languages.
     * @see Description
     */
    val descriptions: Array<Description>,

    /**
     * A list of moves that fall into this damage class.
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