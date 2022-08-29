package entity.moves

import entity.common.NamedApiResource

/**
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class MoveFlavorText(

    /**
     * The localized flavor text for an api resource in a specific language.
     */
    val flavor_text: String,

    /**
     * The language this name is in.
     * @see NamedApiResource
     * @see Language
     */
    val language: NamedApiResource,

    /**
     * The version group that uses this flavor text.
     * @see NamedApiResource
     * @see VersionGroup
     */
    val version_group: NamedApiResource

)