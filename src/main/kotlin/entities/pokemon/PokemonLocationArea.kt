package entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entities.PokeApiObject
import entities.common.NamedApiResource
import entities.common.VersionEncounterDetail
import entities.locations.LocationArea

data class PokemonLocationArea(
    @JsonProperty("location_area")
    val locationArea: NamedApiResource<LocationArea>,
    @JsonProperty("version_details")
    val versionDetails: List<VersionEncounterDetail>
) : PokeApiObject
