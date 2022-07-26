package entity.common

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @link https://pokeapi.co/docs/v2#common-models
 * @author Tykok
 * @version 1.0.0
 * @since 2022-07-27
 */
class Name(

    /**
     * The localized name for an API resource in a specific language
     */
    @JsonProperty("name")
    val name: String,

    /**
     * The language this name is in.
     * @see NamedApiResource
     * @see Language
     */
    @JsonProperty("language")
    val language: NamedApiResource
){
    override fun toString(): String {
        return "Name(name='$name', language=$language)"
    }
}