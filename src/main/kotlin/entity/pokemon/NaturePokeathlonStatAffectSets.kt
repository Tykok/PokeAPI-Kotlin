package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty

class NaturePokeathlonStatAffectSets(
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
) {
    override fun toString(): String {
        return "NaturePokeathlonStatAffectSets(increase=$increase, decrease=$decrease)"
    }
}
