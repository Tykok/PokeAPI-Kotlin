package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource

/**
 * @property isDefault Whether this variety is the default variety.
 * @property pokemon The Pokémon variety.
 */
class PokemonSpeciesVariety(
    @JsonProperty("is_default")
    val isDefault: Boolean,

    @JsonProperty("pokemon")
    val pokemon: NamedApiResource
) {
    override fun toString(): String {
        return "PokemonSpeciesVariety(isDefault=$isDefault, pokemon=$pokemon)"
    }
}