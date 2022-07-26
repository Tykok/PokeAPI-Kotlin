package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource

/**
 * @property genus The localized genus for the referenced Pokémon species
 * @property language The language this genus is in.
 */
class Genus(

    @JsonProperty("genus")
    val genus: String,

    @JsonProperty("language")
    val language: NamedApiResource
) {
    override fun toString(): String {
        return "Genus(genus='$genus', language=$language)"
    }
}