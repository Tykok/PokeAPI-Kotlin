package entity.moves

import entity.common.Name

/**
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-30
 *
 */
class MoveBattleStyle(
    /**
     * The identifier for this resource.
     */
    id: Number,

    /**
     * The name for this resource.
     */
    name: String,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    names: Array<Name>

)