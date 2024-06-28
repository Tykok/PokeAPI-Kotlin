package entities.contests

import com.fasterxml.jackson.annotation.JsonProperty
import entities.common.NamedApiResource
import entities.utility.Language

/**
 * ContestName classe used to refer to a specific categories according to the color of the berry.
 *
 * Please refer to the berry section in the Bulbapedia documentation.
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Contest_condition">Bulbapedia documentation for contest</a>
 * @see <a href="https://pokeapi.co/docs/v2#contestname">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class ContestName(
    /**
     * The name for this contest.
     */
    @JsonProperty("name")
    val name: String,
    /**
     * The color associated with this contest's name.
     */
    @JsonProperty("color")
    val color: String,
    /**
     * The language that this name is in.
     * @see NamedApiResource
     * @see Language
     */
    @JsonProperty("language")
    val language: NamedApiResource<Language>
)
