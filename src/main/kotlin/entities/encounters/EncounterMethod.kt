package entities.encounters

import com.fasterxml.jackson.annotation.JsonProperty
import entities.PokeApiObject
import entities.common.Name

/**
 * EncounterMethod define how you can encounter a Pokemon.
 * Methods by which the player might can encounter a Pokemon in the wild.
 *
 * For example, many Pokemon are encountered in the tall grass.
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Wild_Pok%C3%A9mon">Bulbapedia documentation</a>
 * @see <a href="https://pokeapi.co/docs/v2#encounter-methods">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class EncounterMethod(
    /**
     * The identifier for this resource.
     */
    @JsonProperty("id")
    val id: Number,
    /**
     * The name for this resource.
     */
    @JsonProperty("name")
    val name: String,
    /**
     * A good value for sorting.
     */
    @JsonProperty("order")
    val order: Number,
    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    @JsonProperty("names")
    val names: Array<Name>
) : PokeApiObject
