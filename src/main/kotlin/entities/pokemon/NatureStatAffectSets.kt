package entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entities.common.NamedApiResource

/**
 * @property increase A list of natures and how they change the referenced stat.
 * @property decrease A list of natures and how they change the referenced stat.
 */
data class NatureStatAffectSets(
    @JsonProperty("increase")
    val increase: List<NamedApiResource<Nature>>,
    @JsonProperty("decrease")
    val decrease: List<NamedApiResource<Nature>>
)
