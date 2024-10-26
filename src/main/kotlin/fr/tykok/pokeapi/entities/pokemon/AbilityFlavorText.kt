package fr.tykok.pokeapi.entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.games.VersionGroup
import fr.tykok.pokeapi.entities.utility.Language

data class AbilityFlavorText(
    /**
     * The localized name for an API resource in a specific language.
     */
    @JsonProperty("flavor_text")
    val flavorText: String,
    /**
     * The language this text resource is in.
     * @see NamedApiResource
     * @see Langage
     */
    @JsonProperty("language")
    val language: NamedApiResource<Language>,
    /**
     * The version group that uses this flavor text.
     * @see VersionGroup
     * @see NamedApiResource
     */
    @JsonProperty("version_group")
    val versionGroup: NamedApiResource<VersionGroup>
)
