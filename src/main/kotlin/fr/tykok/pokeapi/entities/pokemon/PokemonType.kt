package fr.tykok.pokeapi.entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.common.NamedApiResource

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
