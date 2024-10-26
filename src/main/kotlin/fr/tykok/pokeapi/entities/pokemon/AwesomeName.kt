package fr.tykok.pokeapi.entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.utility.Language

/**
 * @property awesomeName The localized "scientific" name for an API resource in a specific language.
 * @property language The language this "scientific" name is in.
 */
data class AwesomeName(
    @JsonProperty("awesome_name")
    val awesomeName: String,
    @JsonProperty("language")
    val language: NamedApiResource<Language>
)
