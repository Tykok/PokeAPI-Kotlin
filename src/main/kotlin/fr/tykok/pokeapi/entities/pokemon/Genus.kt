package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.utility.Language

/**
 * @property genus The localized genus for the referenced Pokémon species
 * @property language The language this genus is in.
 */
data class Genus(
    val genus: String,
    val language: NamedApiResource<Language>
) : PokeApiObject
