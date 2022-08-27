package entity.locations

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
    val encounter_method: NamedApiResource,

    /**
     * The chance of the encounter to occur on a version of the game.
     * @see EncounterVersionDetails
     */
    val version_details: EncounterVersionDetails
)