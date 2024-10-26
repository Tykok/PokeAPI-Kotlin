package fr.tykok.pokeapi.entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.moves.MoveBattleStyle

data class MoveBattleStylePreference(
    /**
     * Chance of using the move, in percent, if HP is under one half.
     */
    @JsonProperty("low_hp_preference")
    val lowHpPreference: Number,
    /**
     * Chance of using the move, in percent, if HP is over one half.
     */
    @JsonProperty("high_hp_preference")
    val highHpPreference: Number,
    /**
     * The move battle style.
     * @see MoveBattleStyle
     * @see NamedApiResource
     */
    @JsonProperty("move_battle_style")
    val moveBattleStyle: NamedApiResource<MoveBattleStyle>
)
