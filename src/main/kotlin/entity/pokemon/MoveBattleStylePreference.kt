package entity.pokemon

import entity.common.NamedApiResource
import entity.moves.MoveBattleStyle

class MoveBattleStylePreference(
    /**
     * Chance of using the move, in percent, if HP is under one half.
     */
    val low_hp_preference: Number,

    /**
     * Chance of using the move, in percent, if HP is over one half.
     */
    val high_hp_preference: Number,

    /**
     * The move battle style.
     * @see MoveBattleStyle
     * @see NamedApiResource
     */
    val move_battle_style: NamedApiResource

)