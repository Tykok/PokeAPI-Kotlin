package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource

class AbilityPokemon(

    /**
     * Whether or not this a hidden ability for the referenced Pokémon.
     */
    @JsonProperty("is_hidden")
    val isHidden: Boolean,

    /**
     * Pokémon have 3 ability 'slots' which hold references to possible abilities they could have. This is the slot of this ability for the referenced pokemon.
     */
    @JsonProperty("slot")
    val slot: Number,

    /**
     * The Pokémon this ability could belong to.
     * @see Pokemon
     * @see NamedApiResource
     */
    @JsonProperty("pokemon")
    val pokemon: NamedApiResource<Pokemon>
)
