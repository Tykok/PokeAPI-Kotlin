package entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entities.common.NamedApiResource

data class NaturePokeathlonStatAffect(
    /**
     *  The maximum amount of change to the referenced Pokéathlon stat.
     */
    @JsonProperty("max_change")
    val maxChange: Number,
    /**
     * The nature causing the change.
     * @see Nature
     */
    @JsonProperty("nature")
    val nature: NamedApiResource<Nature>
)
