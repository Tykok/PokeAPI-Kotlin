package entity.common

import com.fasterxml.jackson.annotation.JsonProperty
import entity.games.Version
import entity.utility.Language

/**
 * @see <a href="https://pokeapi.co/docs/v2#common-models">Documentation of PokeApi</a>
 */
class FlavorText(

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
) {
    override fun toString(): String {
        return "FlavorText(flavorText='$flavorText', language=$language, version=$version)"
    }
}
