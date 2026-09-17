package fr.tykok.pokeapi.entities.pokemon
import fr.tykok.pokeapi.annotation.Endpoint
import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.common.VersionEncounterDetail
import fr.tykok.pokeapi.entities.locations.LocationArea

@Endpoint("pokemon-location-area")
public data class PokemonLocationArea(
    val locationArea: NamedApiResource<LocationArea>,
    val versionDetails: List<VersionEncounterDetail>
) : PokeApiEndpointReference
