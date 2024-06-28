package entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entities.common.NamedApiResource
import entities.utility.Language

/**
 * @property genus The localized genus for the referenced Pokémon species
 * @property language The language this genus is in.
 */
data class Genus(
    @JsonProperty("genus")
    val genus: String,
    @JsonProperty("language")
    val language: NamedApiResource<Language>
)
