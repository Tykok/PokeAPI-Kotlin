package entity.common

import com.fasterxml.jackson.annotation.JsonProperty

class NamedApiResources<T : Any>(
    @JsonProperty("count")
    val count: Int,

    @JsonProperty("next")
    val next: String?,

    @JsonProperty("previous")
    val previous: String?,

    @JsonProperty("results")
    val results: List<NamedApiResource<T>>
) {
    override fun toString() =
        "NamedApiResources(count=$count, next=$next, previous=$previous, result=$results)"
}

inline fun <reified T : Any> NamedApiResources<T>.get(): List<T> =
    this.results.map { it.get()!! }
