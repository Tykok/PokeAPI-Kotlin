package fr.tykok.pokeapi.entities.common

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.utility.Language

/**
 * @see <a href="https://pokeapi.co/docs/v2#description">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class Description(
    /**
     * The localized description for an API resource in a specific language.
     */
    val description: String,
    /**
     * The language this name is in.
     * @see NamedApiResource
     * @see Language
     */
    val language: NamedApiResource<Language>
) : PokeApiObject
