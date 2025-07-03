package fr.tykok.pokeapi.entities.locations

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.encounters.EncounterMethod

/**
 * @see <a href="https://pokeapi.co/docs/v2#encountermethodrate">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class EncounterMethodRate(
    /**
     * The method in which Pokémon may be encountered in an area..
     * @see NamedApiResource
     * @see EncounterMethod
     */
    val encounterMethod: NamedApiResource<EncounterMethod>,
    /**
     * The chance of the encounter to occur on a version of the game.
     * @see EncounterVersionDetails
     */
    val versionDetails: List<EncounterVersionDetails>
) : PokeApiObject
