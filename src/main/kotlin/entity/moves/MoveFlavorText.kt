package entity.moves

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource
import entity.games.VersionGroup
import entity.utility.Language

/**
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class MoveFlavorText(

    /**
     * The localized flavor text for an api resource in a specific language.
     */
    @JsonProperty("flavor_text")
    val flavorText: String,

    /**
     * The language this name is in.
     * @see NamedApiResource
     * @see Language
     */
    @JsonProperty("language")
    val language: NamedApiResource<Language>,

    /**
     * The version group that uses this flavor text.
     * @see NamedApiResource
     * @see VersionGroup
     */
    @JsonProperty("version_group")
    val versionGroup: NamedApiResource<VersionGroup>

) {
    override fun toString(): String {
        return "MoveFlavorText(flavorText='$flavorText', language=$language, versionGroup=$versionGroup)"
    }
}
