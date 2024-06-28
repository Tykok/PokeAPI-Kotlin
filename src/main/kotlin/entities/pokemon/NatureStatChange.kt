package entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entities.common.NamedApiResource

data class NatureStatChange(
    /**
     * The amount of change.
     */
    @JsonProperty("max_change")
    val maxChange: Number,
    /**
     * The stat being affected.
     * @see PokeathlonStat
     * @see NamedApiResource
     */
    @JsonProperty("pokeathlon_stat")
    val pokeathlonStat: NamedApiResource<PokeathlonStat>
)
