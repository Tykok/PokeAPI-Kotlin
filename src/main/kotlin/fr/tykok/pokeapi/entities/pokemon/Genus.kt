package fr.tykok.pokeapi.entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.utility.Language

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
