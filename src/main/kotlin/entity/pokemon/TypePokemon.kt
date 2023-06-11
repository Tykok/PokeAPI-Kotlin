package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource

class TypePokemon(

    @JsonProperty("slot")
    val slot: Number,

    @JsonProperty("pokemon")
    val pokemon: NamedApiResource<Pokemon>
) {
    override fun toString(): String {
        return "TypePokemon(slot=$slot, pokemon=$pokemon)"
    }
}
