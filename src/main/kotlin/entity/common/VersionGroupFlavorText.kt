package entity.common

import com.fasterxml.jackson.annotation.JsonProperty
import entity.utility.Language
import entity.games.VersionGroup

/**
 * @see <a href="https://pokeapi.co/docs/v2#versiongroupflavortext">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class VersionGroupFlavorText(

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
    val language: NamedApiResource,

    /**
     * The version group which uses this flavor text.
     * @see NamedApiResource
     * @see VersionGroup
     */
    @JsonProperty("version_group")
    val versionGroup: NamedApiResource
)