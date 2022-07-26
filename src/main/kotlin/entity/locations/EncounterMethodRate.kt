package entity.locations

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource
import entity.encounters.EncounterMethod

/**
 * @see <a href="https://pokeapi.co/docs/v2#encountermethodrate">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class EncounterMethodRate(

    /**
     * The method in which Pokémon may be encountered in an area..
     * @see NamedApiResource
     * @see EncounterMethod
     */
    @JsonProperty("encounter_method")
    val encounterMethod: NamedApiResource,

    /**
     * The chance of the encounter to occur on a version of the game.
     * @see EncounterVersionDetails
     */
    @JsonProperty("version_details")
    val versionDetails: List<EncounterVersionDetails>
) {
    override fun toString(): String {
        return "EncounterMethodRate(encounterMethod=$encounterMethod, versionDetails=$versionDetails)"
    }
}