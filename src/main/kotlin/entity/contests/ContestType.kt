package entity.contests

import entity.NamedApiResource

/**
 * The types given to Pokémon in the contest are categories that judges use to evaluate the condition of a Pokémon in Pokémon contests.
 * You have several possible categories in contests such as :
 *
 * - Coolness
 * - Beauty
 * - Cuteness
 * - Cleverness
 * - Toughness
 *
 * Also, the nature of the Pokémon has a huge impact on its categories.
 *
 * *For more information, please refer to Bulbapedia*.
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Contest_condition">Bulbapedia documentation for contest</a>
 * @see <a href="https://pokeapi.co/docs/v2#contest-types">Documentation of PokeApi</a>
 */
class ContestType(

    /**
     * Identifier of the resource
     */
    val id: Number,

    /**
     * Name of the resource
     */
    val name: String,

    /**
     * The berry flavor that correlates with this contest type.
     * @see NamedApiResource
     */
    val berry_flavor: NamedApiResource,

    /**
     * The name of this contest type listed in different languages.
     * @see ContestName
     */
    val names: Array<ContestName>
)

