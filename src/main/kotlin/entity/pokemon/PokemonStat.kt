package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource

class PokemonStat(
    /**
     * The stat the Pokémon has.
     * @see Stat
     */
    @JsonProperty("stat")
    val stat: NamedApiResource,

    /**
     * The effort points (EV) the Pokémon has in the stat.
     */
    @JsonProperty("effort")
    val effort: Number,

    /**
     * The base value of the stat.
     */
    @JsonProperty("base_stat")
    val baseStat: Number,
)
