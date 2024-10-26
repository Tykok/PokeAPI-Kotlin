package fr.tykok.pokeapi.entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource

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
