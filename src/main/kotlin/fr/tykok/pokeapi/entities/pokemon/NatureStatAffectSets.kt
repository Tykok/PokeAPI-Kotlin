package fr.tykok.pokeapi.entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.common.NamedApiResource

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
