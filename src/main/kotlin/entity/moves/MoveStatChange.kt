package entity.moves

import entity.common.NamedApiResource

/**
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class MoveStatChange(
    /**
     * The amount of change.
     */
    change: Number,

    /**
     * The stat being affected.
     * @see NamedApiResource
     * @see Stat
     */
    stat: NamedApiResource

)