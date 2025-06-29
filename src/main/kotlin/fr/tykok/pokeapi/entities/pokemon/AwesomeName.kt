package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.utility.Language

/**
 * @property awesomeName The localized "scientific" name for an API resource in a specific language.
 * @property language The language this "scientific" name is in.
 */
data class AwesomeName(
    val awesomeName: String,
    val language: NamedApiResource<Language>
) : PokeApiObject
