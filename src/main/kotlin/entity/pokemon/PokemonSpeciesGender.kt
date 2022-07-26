package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource

class PokemonSpeciesGender(
    /**
     * The chance of this Pokémon being female, in eighths; or -1 for genderless.
     */
    @JsonProperty("rate")
    val rate: Number,

    /**
     * A Pokémon species that can be the referenced gender.
     * @see PokemonSpecies
     * @see NamedApiResource
     */
    @JsonProperty("pokemon_species")
    val pokemonSpecies: NamedApiResource
) {
    override fun toString(): String {
        return "rate = $rate pokemonSpecies = $pokemonSpecies"
    }
}