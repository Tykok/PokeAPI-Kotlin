package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource
import entity.games.Pokedex

/**
 * @property entryNumber The index number within the Pokédex.
 * @property pokedex The Pokédex the referenced Pokémon species can be found in.
 */
class PokemonSpeciesDexEntry(
    @JsonProperty("entry_number")
    val entryNumber: Number,

    @JsonProperty("pokedex")
    val pokedex: NamedApiResource<Pokedex>
) {
    override fun toString(): String {
        return "PokemonSpeciesDexEntry(entryNumber=$entryNumber, pokedex=$pokedex)"
    }
}
