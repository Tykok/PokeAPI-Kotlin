package entity.pokemon

import entity.common.NamedApiResource

class Gender(
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
    val pokemon_species_details: Array<PokemonSpeciesGender>,

    /**
     * A list of Pokémon species that required this gender in order for a Pokémon to evolve into them.
     * @see PokemonSpecies
     * @see NamedApiResource
     */
    val required_for_evolution: Array<NamedApiResource>,
)