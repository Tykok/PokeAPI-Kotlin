package entity.contests

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.Effect
import entity.common.FlavorText

/**
 * `ContestEffects` class refers to the effects of move on the public when used in contests.
 * When you make a contest with your Pokemon, the public has a reaction according to the nature and the movement of your Pokemon.
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Contest_condition">Bulbapedia documentation for contest</a>
 * @see <a href="https://pokeapi.co/docs/v2#contest-effects">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class ContestEffect(

    /**
     * The identifier for this resource.
     */
    @JsonProperty("id")
    val id: Number,

    /**
     * The base number of hearts the user of this move gets.
     */
    @JsonProperty("appeal")
    val appeal: Number,

    /**
     * The base number of hearts the user's opponent loses.
     */
    @JsonProperty("jam")
    val jam: Number,

    /**
     * The result of this contest effect listed in different languages.
     * @see Effect
     */
    @JsonProperty("effect_entries")
    val effectEntries: Array<Effect>,

    /**
     * The flavor text of this contest effect listed in different languages.
     * @see FlavorText
     */
    @JsonProperty("flavor_text_entries")
    val flavorTextEntries: Array<FlavorText>
) {
    override fun toString(): String {
        return "ContestEffect(id=$id, appeal=$appeal, jam=$jam, effectEntries=${effectEntries.contentToString()}, flavorTextEntries=${flavorTextEntries.contentToString()})"
    }
}