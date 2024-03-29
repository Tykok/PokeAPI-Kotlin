package entity.contests

import com.fasterxml.jackson.annotation.JsonProperty
import entity.berries.BerryFlavor
import entity.common.NamedApiResource

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
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class ContestType(

    /**
     * Identifier of the resource
     */
    @JsonProperty("id")
    val id: Number,

    /**
     * Name of the resource
     */
    @JsonProperty("name")
    val name: String,

    /**
     * The berry flavor that correlates with this contest type.
     * @see NamedApiResource
     * @see BerryFlavor
     */
    @JsonProperty("berry_flavor")
    val berryFlavor: NamedApiResource<BerryFlavor>,

    /**
     * The name of this contest type listed in different languages.
     * @see ContestName
     */
    @JsonProperty("names")
    val names: Array<ContestName>
) {
    override fun toString() =
        "ContestType(id=$id, name='$name', berryFlavor=$berryFlavor, names=${names.contentToString()})"
}
