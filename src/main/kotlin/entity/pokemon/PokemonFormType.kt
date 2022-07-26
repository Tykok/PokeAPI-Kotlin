package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource

class PokemonFormType(
    /**
     * The order the Pokémon's types are listed in.
     */
    @JsonProperty("slot")
    val slot: Number,

    /**
     * The type the referenced Form has.
     * @see Type
     */
    @JsonProperty("type")
    val type: NamedApiResource
) {
    override fun toString(): String {
        return "slot=$slot, type=$type"
    }
}
