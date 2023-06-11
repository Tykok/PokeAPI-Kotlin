package entity.common

import com.fasterxml.jackson.annotation.JsonProperty
import entity.utility.Language

/**
 * @see <a href="https://pokeapi.co/docs/v2#verboseeffect">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class VerboseEffect(

    /**
     * The localized effect text for an API resource in a specific language.
     */
    @JsonProperty("effect")
    val effect: String,

    /**
     * The localized effect text in brief.
     */
    @JsonProperty("short_effect")
    val shortEffect: String,

    /**
     * The language this effect is in.
     * @see NamedApiResource
     * @see Language
     */
    @JsonProperty("language")
    val language: NamedApiResource<Language>
)
