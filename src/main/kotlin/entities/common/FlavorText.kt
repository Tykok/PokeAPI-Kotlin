package entities.common

import com.fasterxml.jackson.annotation.JsonProperty
import entities.games.Version
import entities.utility.Language

/**
 * @see <a href="https://pokeapi.co/docs/v2#common-models">Documentation of PokeApi</a>
 */
data class FlavorText(
    /**
     * The localized flavor text for an API resource in a specific language
     */
    @JsonProperty("flavor_text")
    val flavorText: String,
    /**
     * The language this name is in.
     * @see NamedApiResource
     * @see Language
     */
    @JsonProperty("language")
    val language: NamedApiResource<Language>,
    /**
     * The game version this flavor text is extracted from.
     * @see NamedApiResource
     * @see Version
     */
    @JsonProperty("version")
    val version: NamedApiResource<Version>?
)
