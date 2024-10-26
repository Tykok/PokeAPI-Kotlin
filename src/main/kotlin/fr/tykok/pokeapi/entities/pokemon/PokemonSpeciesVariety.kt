package fr.tykok.pokeapi.entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.common.NamedApiResource

/**
 * @property isDefault Whether this variety is the default variety.
 * @property pokemon The Pokémon variety.
 */
data class PokemonSpeciesVariety(
    @JsonProperty("is_default")
    val isDefault: Boolean,
    @JsonProperty("pokemon")
    val pokemon: NamedApiResource<Pokemon>
)
