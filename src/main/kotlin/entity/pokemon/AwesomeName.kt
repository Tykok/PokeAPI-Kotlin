package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource

/**
 * @property awesomeName The localized "scientific" name for an API resource in a specific language.
 * @property language The language this "scientific" name is in.
 */
class AwesomeName(
    @JsonProperty("awesome_name")
    val awesomeName: String,

    @JsonProperty("language")
    val language: NamedApiResource
) {
    override fun toString(): String {
        return "AwesomeName(awesomeName='$awesomeName', language=$language)"
    }
}
