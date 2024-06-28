package entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entities.PokeApiObject
import entities.common.NamedApiResource

data class Gender(
    /**
     * The identifier for this resource.
     */
    @JsonProperty("id")
    val id: Number,
    /**
     * The name for this resource.
     */
    @JsonProperty("name")
    val name: String,
    /**
     * A list of Pokémon species that can be this gender and how likely it is that they will be.
     * @see PokemonSpeciesGender
     */
    @JsonProperty("pokemon_species_details")
    val pokemonSpeciesDetails: List<PokemonSpeciesGender>,
    /**
     * A list of Pokémon species that required this gender in order for a Pokémon to evolve into them.
     * @see PokemonSpecies
     * @see NamedApiResource
     */
    @JsonProperty("required_for_evolution")
    val requiredForEvolution: List<NamedApiResource<PokemonSpecies>>
) : PokeApiObject
