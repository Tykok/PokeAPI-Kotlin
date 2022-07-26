package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource

class PokemonType(
    /**
     * The order the Pokémon's types are listed in.
     */
    @JsonProperty("slot")
    val slot: Number,

    /**
     * The type the referenced Pokémon has.
     * @see Type
     */
    @JsonProperty("type")
    val type: NamedApiResource
)
