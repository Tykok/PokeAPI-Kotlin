package fr.tykok.pokeapi.entities.common

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.games.VersionGroup
import fr.tykok.pokeapi.entities.utility.Language

/**
 * @see <a href="https://pokeapi.co/docs/v2#versiongroupflavortext">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class VersionGroupFlavorText(
    /**
     * The localized name for an API resource in a specific language.
     */
    @JsonProperty("text")
    val text: String,
    /**
     * The language this name is in.
     * @see NamedApiResource
     * @see Language
     */
    @JsonProperty("language")
    val language: NamedApiResource<Language>,
    /**
     * The version group which uses this flavor text.
     * @see NamedApiResource
     * @see VersionGroup
     */
    @JsonProperty("version_group")
    val versionGroup: NamedApiResource<VersionGroup>
)
