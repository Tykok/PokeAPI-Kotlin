package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource

/**
 * @property entryNumber The index number within the Pokédex.
 * @property pokedex The Pokédex the referenced Pokémon species can be found in.
 */
class PokemonSpeciesDexEntry(
    @JsonProperty("entry_number")
    val entryNumber: Number,

    @JsonProperty("pokedex")
    val pokedex: NamedApiResource
) {
    override fun toString(): String {
        return "PokemonSpeciesDexEntry(entryNumber=$entryNumber, pokedex=$pokedex)"
    }
}
