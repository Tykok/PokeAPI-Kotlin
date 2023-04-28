package entity.locations

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource
import entity.games.Version

/**
 * @see <a href="https://pokeapi.co/docs/v2#encounterversiondetails">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class EncounterVersionDetails(

    /**
     * The chance of an encounter to occur.
     */
    @JsonProperty("rate")
    val rate: Number,

    /**
     * The version of the game in which the encounter can occur with the given chance.
     * @see NamedApiResource
     * @see Version
     */
    @JsonProperty("version")
    val version: NamedApiResource
) {
    override fun toString(): String {
        return "EncounterVersionDetails(rate=$rate, version=$version)"
    }
}
