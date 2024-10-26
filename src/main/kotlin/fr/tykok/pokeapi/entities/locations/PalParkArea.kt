package fr.tykok.pokeapi.entities.locations

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.Name

/**
 * @see <a href="https://pokeapi.co/docs/v2#pal-park-areas">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class PalParkArea(
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
     * The name of this resource listed in different languages.
     * @see Name
     */
    @JsonProperty("names")
    val names: List<Name>,
    /**
     * A list of Pokémon encountered in thi pal park area along with details.
     * @see PalParkEncounterSpecies
     */
    @JsonProperty("pokemon_encounters")
    val pokemonEncounters: List<PalParkEncounterSpecies>
) : PokeApiObject
