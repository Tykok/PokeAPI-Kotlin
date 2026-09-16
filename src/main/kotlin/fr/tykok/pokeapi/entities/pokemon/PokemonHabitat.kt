package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.annotation.Endpoint
import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource

@Endpoint("pokemon-habitat")
data class PokemonHabitat(
    val id: Number,
    val name: String,
    val names: List<Name>,
    val pokemonSpecies: List<NamedApiResource<PokemonSpecies>>
) : PokeApiEndpointReference
