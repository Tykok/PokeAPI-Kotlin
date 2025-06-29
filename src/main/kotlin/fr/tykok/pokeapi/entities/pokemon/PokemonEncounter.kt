package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.common.VersionEncounterDetail
import fr.tykok.pokeapi.entities.locations.LocationArea

/**
 * A class representing the encounter details of a Pokémon.
 *
 * @property locationArea The location area the referenced Pokémon can be encountered in.
 * @property versionDetails A list of versions and encounters with the referenced Pokémon that might happen.
 */
data class PokemonEncounter(
    val locationArea: NamedApiResource<LocationArea>,
    val versionDetails: List<VersionEncounterDetail>
)
