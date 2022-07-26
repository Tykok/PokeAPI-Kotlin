package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource
import entity.common.VersionEncounterDetail

class PokemonLocationArea(
    @JsonProperty("location_area")
    val locationArea: NamedApiResource,

    @JsonProperty("version_details")
    val versionDetails: List<VersionEncounterDetail>
) {
    override fun toString(): String {
        return "PokemonLocationArea(locationArea=$locationArea, versionDetails=$versionDetails)"
    }
}
