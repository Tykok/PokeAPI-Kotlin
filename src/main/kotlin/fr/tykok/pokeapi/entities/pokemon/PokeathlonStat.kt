package fr.tykok.pokeapi.entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.Name

data class PokeathlonStat(
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
     */
    @JsonProperty("names")
    val names: List<Name>,
    /**
     * A detail of natures which affect this Pokéathlon stat positively or negatively.
     */
    @JsonProperty("affecting_natures")
    val affectingNatures: NaturePokeathlonStatAffectSets
) : PokeApiObject
