package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.NamedApiResource

data class Gender(
    /**
     * The identifier for this resource.
     */
    val id: Number,
    /**
     * The name for this resource.
     */
    val name: String,
    /**
     * A list of Pokémon species that can be this gender and how likely it is that they will be.
     * @see PokemonSpeciesGender
     */
    val pokemonSpeciesDetails: List<PokemonSpeciesGender>,
    /**
     * A list of Pokémon species that required this gender in order for a Pokémon to evolve into them.
     * @see PokemonSpecies
     * @see NamedApiResource
     */
    val requiredForEvolution: List<NamedApiResource<PokemonSpecies>>
) : PokeApiEndpointReference
