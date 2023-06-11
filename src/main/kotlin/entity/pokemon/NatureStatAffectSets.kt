package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource

/**
 * @property increase A list of natures and how they change the referenced stat.
 * @property decrease A list of natures and how they change the referenced stat.
 */
class NatureStatAffectSets(

    @JsonProperty("increase")
    val increase: List<NamedApiResource<Nature>>,

    @JsonProperty("decrease")
    val decrease: List<NamedApiResource<Nature>>
) {
    override fun toString(): String {
        return "NatureStatAffectSets(increase=$increase, decrease=$decrease)"
    }
}
