package fr.tykok.pokeapi.entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.common.VersionEncounterDetail
import fr.tykok.pokeapi.entities.locations.LocationArea

data class PokemonLocationArea(
    @JsonProperty("location_area")
    val locationArea: NamedApiResource<LocationArea>,
    @JsonProperty("version_details")
    val versionDetails: List<VersionEncounterDetail>
) : PokeApiObject
