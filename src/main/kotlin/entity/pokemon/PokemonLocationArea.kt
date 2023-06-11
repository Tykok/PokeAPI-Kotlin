package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource
import entity.common.VersionEncounterDetail
import entity.locations.LocationArea

class PokemonLocationArea(
    @JsonProperty("location_area")
    val locationArea: NamedApiResource<LocationArea>,

    @JsonProperty("version_details")
    val versionDetails: List<VersionEncounterDetail>
) {
    override fun toString(): String {
        return "PokemonLocationArea(locationArea=$locationArea, versionDetails=$versionDetails)"
    }
}
