package entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entities.common.NamedApiResource

data class PokemonFormType(
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
    val type: NamedApiResource<Type>
)
