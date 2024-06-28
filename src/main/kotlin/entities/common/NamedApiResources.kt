package entities.common

import com.fasterxml.jackson.annotation.JsonProperty

data class NamedApiResources<T : Any>(
    @JsonProperty("count")
    val count: Int,
    @JsonProperty("next")
    val next: String?,
    @JsonProperty("previous")
    val previous: String?,
    @JsonProperty("results")
    val results: List<NamedApiResource<T>>
)

inline fun <reified T : Any> NamedApiResources<T>.get(): List<T> = this.results.map { it.get()!! }
