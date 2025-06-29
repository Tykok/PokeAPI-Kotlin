package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.games.VersionGroup
import fr.tykok.pokeapi.entities.utility.Language

data class AbilityFlavorText(
    /**
     * The localized name for an API resource in a specific language.
     */
    val flavorText: String,
    /**
     * The language this text resource is in.
     * @see NamedApiResource
     * @see Langage
     */
    val language: NamedApiResource<Language>,
    /**
     * The version group that uses this flavor text.
     * @see VersionGroup
     * @see NamedApiResource
     */
    val versionGroup: NamedApiResource<VersionGroup>
) : PokeApiObject
