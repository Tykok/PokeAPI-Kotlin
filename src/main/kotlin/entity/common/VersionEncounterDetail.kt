package entity.common

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
    val version: NamedApiResource,

    /**
     * The total percentage of all encounter potential.
     */
    val max_chance: Number,

    /**
     * A list of encounters and their specifics.
     */
    val encounter_details: Array<Encounter>

)