package fr.tykok.pokeapi.entities.common

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.games.Version

/**
 * @see <a href="https://pokeapi.co/docs/v2#versionencounterdetail">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class VersionEncounterDetail(
    /**
     * The game version this encounter happens in.
     * @see NamedApiResource
     * @see Version
     */
    val version: NamedApiResource<Version>,
    /**
     * The total percentage of all encounter potential.
     */
    val maxChance: Number,
    /**
     * A list of encounters and their specifics.
     */
    val encounterDetails: List<Encounter>
) : PokeApiObject
