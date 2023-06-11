package entity.common

import com.fasterxml.jackson.annotation.JsonProperty
import entity.games.Version

/**
 * @see <a href="https://pokeapi.co/docs/v2#versionencounterdetail">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class VersionEncounterDetail(

    /**
     * The game version this encounter happens in.
     * @see NamedApiResource
     * @see Version
     */
    @JsonProperty("version")
    val version: NamedApiResource<Version>,

    /**
     * The total percentage of all encounter potential.
     */
    @JsonProperty("max_chance")
    val maxChance: Number,

    /**
     * A list of encounters and their specifics.
     */
    @JsonProperty("encounter_details")
    val encounterDetails: List<Encounter>

) {
    override fun toString() =
        "VersionEncounterDetail(version=$version, maxChance=$maxChance, encounterDetails=$encounterDetails)"
}
