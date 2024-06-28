package entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entities.PokeApiObject
import entities.common.Name
import entities.common.NamedApiResource

data class PokemonHabitat(
    @JsonProperty("id")
    val id: Number,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("names")
    val names: List<Name>,
    @JsonProperty("pokemon_species")
    val pokemonSpecies: List<NamedApiResource<PokemonSpecies>>
) : PokeApiObject
