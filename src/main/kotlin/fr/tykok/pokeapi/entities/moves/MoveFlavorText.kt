package fr.tykok.pokeapi.entities.moves
import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.games.VersionGroup
import fr.tykok.pokeapi.entities.utility.Language

/**
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class MoveFlavorText(
    /**
     * The localized flavor text for an api resource in a specific language.
     */
    val flavorText: String,
    /**
     * The language this name is in.
     * @see NamedApiResource
     * @see Language
     */
    val language: NamedApiResource<Language>,
    /**
     * The version group that uses this flavor text.
     * @see NamedApiResource
     * @see VersionGroup
     */
    val versionGroup: NamedApiResource<VersionGroup>
) : PokeApiObject
