package entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entities.common.NamedApiResource

data class TypePokemon(
    @JsonProperty("slot")
    val slot: Number,
    @JsonProperty("pokemon")
    val pokemon: NamedApiResource<Pokemon>
)
