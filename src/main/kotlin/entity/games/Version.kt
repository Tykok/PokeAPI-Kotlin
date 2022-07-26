package entity.games

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.Name
import entity.common.NamedApiResource

/**
 * Versions of the games, e.g., Red, Blue or Yellow.
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Core_series">Bulbapedia documentation</a>
 * @see <a href="https://pokeapi.co/docs/v2#version">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class Version (
    /**
     * The identifier for this resource.
     */
    @JsonProperty("id")
    val id: Number,

    /**
     * The name for this resource.
     */
    @JsonProperty("name")
    val name: String,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    @JsonProperty("names")
    val names: Array<Name>,

    /**
     * A list of version groups this Pokédex is relevant to.
     * @see NamedApiResource
     * @see VersionGroup
     */
    @JsonProperty("version_group")
    val versionGroup: NamedApiResource? = null
) {
    override fun toString(): String {
        return "Version(id=$id, name='$name', names=${names.contentToString()}, versionGroups=$versionGroup)"
    }
}