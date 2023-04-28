package entity.common

import com.fasterxml.jackson.annotation.JsonProperty

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
     */
    @JsonProperty("language")
    val language: NamedApiResource,

    /**
     * The game version this flavor text is extracted from.
     * @see NamedApiResource
     */
    @JsonProperty("version")
    val version: NamedApiResource?
) {
    override fun toString(): String {
        return "FlavorText(flavorText='$flavorText', language=$language, version=$version)"
    }
}
