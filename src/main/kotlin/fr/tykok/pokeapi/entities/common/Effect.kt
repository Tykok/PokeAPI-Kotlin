package fr.tykok.pokeapi.entities.common

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.utility.Language

/**
 * @see <a href="https://pokeapi.co/docs/v2#common-models">Documentation of PokeApi</a>
 */
data class Effect(
    /**
     * The localized effect text for an API resource in a specific language
     */
    val effect: String,
    /**
     * The language this effect is in.
     * @see NamedApiResource
     * @see Language
     */
    val language: NamedApiResource<Language>
) : PokeApiObject
