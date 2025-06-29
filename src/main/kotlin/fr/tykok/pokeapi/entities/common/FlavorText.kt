package fr.tykok.pokeapi.entities.common

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.games.Version
import fr.tykok.pokeapi.entities.utility.Language

/**
 * @see <a href="https://pokeapi.co/docs/v2#common-models">Documentation of PokeApi</a>
 */
data class FlavorText(
    /**
     * The localized flavor text for an API resource in a specific language
     */
    val flavorText: String,
    /**
     * The language this name is in.
     * @see NamedApiResource
     * @see Language
     */
    val language: NamedApiResource<Language>,
    /**
     * The game version this flavor text is extracted from.
     * @see NamedApiResource
     * @see Version
     */
    val version: NamedApiResource<Version>?
) : PokeApiObject
