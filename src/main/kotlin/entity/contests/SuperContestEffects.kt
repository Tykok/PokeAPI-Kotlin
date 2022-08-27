package entity.contests

import entity.common.FlavorText
import entity.common.NamedApiResource

/**
 * `SuperContestEffects` class refers to the effects of move on the public when used in super contests.
 * When you make a super contest with your Pokemon, the public has a reaction according to the nature and the movement of your Pokemon.
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Contest_condition">Bulbapedia documentation for contest</a>
 * @see <a href="https://pokeapi.co/docs/v2#contest-effects">Documentation of PokeApi</a>
 * @see ContestEffect
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class SuperContestEffects(

    /**
     * The identifier for this resource.
     */
    val id: Number,

    /**
     * The level of appeal this super contest effect has.
     */
    val appeal: Number,

    /**
     * The flavor text of this super contest effect listed in different languages.
     * @see FlavorText
     */
    val flavor_text_entries: Array<FlavorText>,

    /**
     * A list of moves that have the effect when used in super contests.
     * @see NamedApiResource
     */
    val moves: NamedApiResource,
)