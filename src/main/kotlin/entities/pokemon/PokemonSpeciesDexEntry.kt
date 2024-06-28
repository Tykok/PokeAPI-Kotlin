package entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entities.common.NamedApiResource
import entities.games.Pokedex

/**
 * @property entryNumber The index number within the Pokédex.
 * @property pokedex The Pokédex the referenced Pokémon species can be found in.
 */
data class PokemonSpeciesDexEntry(
    @JsonProperty("entry_number")
    val entryNumber: Number,
    @JsonProperty("pokedex")
    val pokedex: NamedApiResource<Pokedex>
)
