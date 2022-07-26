package entity.common

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * NamedApiResource contains the name and the url to get the object from the API resource (pokeapi.co).
 * @link https://bulbapedia.bulbagarden.net/wiki/Berry
 * @author Tykok
 * @version 1.0.0
 * @since 2022-07-27
 */
class NamedApiResource(

    /**
     * The name of the referenced resource.
     */
    @JsonProperty("name")
    val name: String,

    /**
     * The URL of the referenced resource.
     */
    @JsonProperty("url")
    val url: String? = null,

) {
    override fun toString(): String {
        return "NamedApiResource(name='$name', url=$url)"
    }
}