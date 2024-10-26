package fr.tykok.pokeapi.entities.contests

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.FlavorText
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.moves.Move

/**
 * `SuperContestEffects` class refers to the effects of move on the public when used in super contests.
 * When you make a super contest with your Pokémon, the public has a reaction according to the nature and the movement of your Pokémon.
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Contest_condition">Bulbapedia documentation for contest</a>
 * @see <a href="https://pokeapi.co/docs/v2#contest-effects">Documentation of PokeApi</a>
 * @see ContestEffect
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class SuperContestEffects(
    /**
     * The identifier for this resource.
     */
    @JsonProperty("id")
    val id: Number,
    /**
     * The level of appeal this super contest effect has.
     */
    @JsonProperty("appeal")
    val appeal: Number,
    /**
     * The flavor text of this super contest effect listed in different languages.
     * @see FlavorText
     */
    @JsonProperty("flavor_text_entries")
    val flavorTextEntries: Array<FlavorText>,
    /**
     * A list of moves that have the effect when used in super contests.
     * @see NamedApiResource
     * @see Move
     */
    @JsonProperty("moves")
    val moves: List<NamedApiResource<Move>>
) : PokeApiObject
