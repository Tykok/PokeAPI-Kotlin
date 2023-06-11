package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource

class NatureStatChange(
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
) {
    override fun toString(): String {
        return "NatureStatChange(maxChange=$maxChange, pokeathlonStat=$pokeathlonStat)"
    }
}
