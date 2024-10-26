package fr.tykok.pokeapi.entities.common

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.utility.Language

/**
 * @link https://pokeapi.co/docs/v2#common-models
 * @author Tykok
 * @version 1.0.0
 * @since 2022-07-27
 */
data class Name(
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
    val language: NamedApiResource<Language>
)
