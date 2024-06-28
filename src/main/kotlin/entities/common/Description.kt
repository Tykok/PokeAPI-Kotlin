package entities.common

import com.fasterxml.jackson.annotation.JsonProperty
import entities.utility.Language

/**
 * @see <a href="https://pokeapi.co/docs/v2#description">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class Description(
    /**
     * The localized description for an API resource in a specific language.
     */
    @JsonProperty("description")
    val description: String,
    /**
     * The language this name is in.
     * @see NamedApiResource
     * @see Language
     */
    @JsonProperty("language")
    val language: NamedApiResource<Language>
)
