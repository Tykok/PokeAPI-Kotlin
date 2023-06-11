package entity.utility

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.Name

/**
 * Languages for translations of API resource information.
 * @link https://pokeapi.co/docs/v2#languages
 * @author Tykok
 * @version 1.0.0
 * @since 2022-07-27
 */
class Language(

    /**
     * The identifier for this resource
     */
    @JsonProperty("id")
    val id: Number,

    /**
     * The name for this resource.
     */
    @JsonProperty("name")
    val name: String,

    /**
     * Whether the games are published in this language.
     */
    @JsonProperty("official")
    val official: Boolean,

    /**
     * The two-letter code of the country where this language is spoken. Note that it is not unique.
     */
    @JsonProperty("iso639")
    val iso639: String,

    /**
     * The two-letter code of the language. Note that it is not unique.
     */
    @JsonProperty("iso3166")
    val iso3166: String,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    @JsonProperty("names")
    val names: Array<Name>

) {
    override fun toString() =
        "Language(id=$id, name=$name, official=$official, iso639=$iso639, iso3166=$iso3166, " +
            "names=${names.contentToString()})"
}
