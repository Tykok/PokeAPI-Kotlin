package entity.common

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
    val effect: String,

    /**
     * The localized effect text in brief.
     */
    val short_effect: String,

    /**
     * The language this effect is in.
     * @see NamedApiResource
     * @see Language
     */
    val language: NamedApiResource
)