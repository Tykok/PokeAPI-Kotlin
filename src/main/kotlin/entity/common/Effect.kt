package entity.common

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @see <a href="https://pokeapi.co/docs/v2#common-models">Documentation of PokeApi</a>
 */
class Effect (
    /**
     * The localized effect text for an API resource in a specific language
     */
    @JsonProperty("effect")
    val effect: String,

    /**
     * The language this effect is in.
     * @see NamedApiResource
     */
    @JsonProperty("language")
    val language: NamedApiResource,
) {
    override fun toString(): String {
        return "Effect(effect='$effect', language=$language)"
    }
}