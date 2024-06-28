package entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entities.common.NamedApiResource
import entities.common.VersionEncounterDetail
import entities.locations.LocationArea

/**
 * A class representing the encounter details of a Pokémon.
 *
 * @property locationArea The location area the referenced Pokémon can be encountered in.
 * @property versionDetails A list of versions and encounters with the referenced Pokémon that might happen.
 */
data class PokemonEncounter(
    @JsonProperty("location_area")
    val locationArea: NamedApiResource<LocationArea>,
    @JsonProperty("version_details")
    val versionDetails: Array<VersionEncounterDetail>
)
