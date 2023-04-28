package entity.common

import com.fasterxml.jackson.annotation.JsonProperty

class VersionGameIndex(
    @JsonProperty("game_index")
    val gameIndex: Int,

    @JsonProperty("version")
    val version: NamedApiResource
)
