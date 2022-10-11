package entity.pokemon

import entity.common.NamedApiResource
import entity.games.VersionGroup
import entity.utility.Language
class AbilityFlavorText(
    /**
     * The localized name for an API resource in a specific language.
     */
    val flavor_text: String,

    /**
     * The language this text resource is in.
     * @see NamedApiResource
     * @see Langage
     */
    val language: NamedApiResource,

    /**
     * The version group that uses this flavor text.
     * @see VersionGroup
     * @see NamedApiResource
     */
    val version_group: NamedApiResource
)