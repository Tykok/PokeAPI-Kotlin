package fr.tykok.pokeapi.entities.contests

import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.Effect
import fr.tykok.pokeapi.entities.common.FlavorText

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
data class ContestEffect(
    /**
     * The identifier for this resource.
     */
    val id: Number,
    /**
     * The base number of hearts the user of this move gets.
     */
    val appeal: Number,
    /**
     * The base number of hearts the user's opponent loses.
     */
    val jam: Number,
    /**
     * The result of this contest effect listed in different languages.
     * @see Effect
     */
    val effectEntries: List<Effect>,
    /**
     * The flavor text of this contest effect listed in different languages.
     * @see FlavorText
     */
    val flavorTextEntries: List<FlavorText>
) : PokeApiEndpointReference
