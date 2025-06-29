package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.moves.MoveBattleStyle

data class MoveBattleStylePreference(
    /**
     * Chance of using the move, in percent, if HP is under one half.
     */
    val lowHpPreference: Number,
    /**
     * Chance of using the move, in percent, if HP is over one half.
     */
    val highHpPreference: Number,
    /**
     * The move battle style.
     * @see MoveBattleStyle
     * @see NamedApiResource
     */
    val moveBattleStyle: NamedApiResource<MoveBattleStyle>
)
