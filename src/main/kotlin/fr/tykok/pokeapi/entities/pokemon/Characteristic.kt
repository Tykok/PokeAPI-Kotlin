package fr.tykok.pokeapi.entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.Description
import fr.tykok.pokeapi.entities.common.NamedApiResource

data class Characteristic(
    /**
     * The identifier for this resource.
     */
    @JsonProperty("id")
    val id: Number,
    /**
     * The remainder of the highest stat/IV divided by 5.
     */
    @JsonProperty("gene_modulo")
    val geneModulo: Number,
    /**
     * The possible values of the highest stat that would result in a Pokémon recieving this characteristic when divided by 5.
     */
    @JsonProperty("possible_values")
    val possibleValues: List<Number>,
    @JsonProperty("descriptions")
    val descriptions: List<Description>,
    /**
     * The stat which is affected by this characteristic.
     */
    @JsonProperty("highest_stat")
    val highestStat: NamedApiResource<Stat>
) : PokeApiObject
