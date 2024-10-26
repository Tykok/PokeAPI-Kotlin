package fr.tykok.pokeapi.entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.common.NamedApiResource

data class PokemonStat(
    /**
     * The stat the Pokémon has.
     * @see Stat
     */
    @JsonProperty("stat")
    val stat: NamedApiResource<Stat>,
    /**
     * The effort points (EV) the Pokémon has in the stat.
     */
    @JsonProperty("effort")
    val effort: Number,
    /**
     * The base value of the stat.
     */
    @JsonProperty("base_stat")
    val baseStat: Number
)
