package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource
import entity.common.VersionEncounterDetail
import entity.locations.LocationArea

/**
 * A class representing the encounter details of a Pokémon.
 *
 * @property locationArea The location area the referenced Pokémon can be encountered in.
 * @property versionDetails A list of versions and encounters with the referenced Pokémon that might happen.
 */
class PokemonEncounter(

    @JsonProperty("location_area")
    val locationArea: NamedApiResource<LocationArea>,

    @JsonProperty("version_details")
    val versionDetails: Array<VersionEncounterDetail>
) {
    override fun toString(): String {
        return "PokemonEncounter(locationArea=$locationArea, versionDetails=${versionDetails.contentToString()})"
    }
}
