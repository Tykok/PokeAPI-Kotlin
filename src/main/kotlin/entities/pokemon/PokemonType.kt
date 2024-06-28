package entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entities.common.NamedApiResource

data class PokemonType(
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
    val type: NamedApiResource<Type>
)
