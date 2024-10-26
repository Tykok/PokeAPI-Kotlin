package fr.tykok.pokeapi.entities.common

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.games.Version

data class VersionGameIndex(
    @JsonProperty("game_index")
    val gameIndex: Int,
    /**
     * The version relevent to this game index.
     * @see NamedApiResource
     * @see Version
     */
    @JsonProperty("version")
    val version: NamedApiResource<Version>
)
