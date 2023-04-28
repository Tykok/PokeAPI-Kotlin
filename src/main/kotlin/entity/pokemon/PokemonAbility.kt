package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource

class PokemonAbility(
    /**
     *
     * Whether or not this is a hidden ability.
     */
    @JsonProperty("is_hidden")
    val isHidden: Boolean,

    /**
     * The slot this ability occupies in this Pokémon species.
     */
    @JsonProperty("slot")
    val slot: Int,

    /**
     * The ability the Pokémon may have.
     * @see Ability
     */
    @JsonProperty("ability")
    val ability: NamedApiResource
)
