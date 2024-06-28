package entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty

data class NaturePokeathlonStatAffectSets(
    /**
     * A list of natures and how they change the referenced Pokéathlon stat.
     */
    @JsonProperty("increase")
    val increase: List<NaturePokeathlonStatAffect>,
    /**
     * A list of natures and how they change the referenced Pokéathlon stat.
     */
    @JsonProperty("decrease")
    val decrease: List<NaturePokeathlonStatAffect>
)
