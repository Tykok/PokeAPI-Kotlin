package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.Name
import entity.common.NamedApiResource

class PokemonHabitat(

    @JsonProperty("id")
    val id: Number,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("names")
    val names: List<Name>,

    @JsonProperty("pokemon_species")
    val pokemonSpecies: List<NamedApiResource<PokemonSpecies>>
) {
    override fun toString(): String {
        return "PokemonHabitat(id=$id, name='$name', names=$names, pokemonSpecies=$pokemonSpecies)"
    }
}
