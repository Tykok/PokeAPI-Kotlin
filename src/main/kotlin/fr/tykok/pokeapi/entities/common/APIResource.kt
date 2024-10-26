package fr.tykok.pokeapi.entities.common

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @see <a href="https://pokeapi.co/docs/v2#apiresource">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class APIResource(
    /**
     *The URL of the referenced resource.
     */
    @JsonProperty("url")
    val url: String
)
