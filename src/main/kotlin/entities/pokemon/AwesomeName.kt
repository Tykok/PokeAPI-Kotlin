package entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entities.common.NamedApiResource
import entities.utility.Language

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
