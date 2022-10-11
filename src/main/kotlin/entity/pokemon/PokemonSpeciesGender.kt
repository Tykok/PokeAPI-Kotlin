package entity.pokemon

import entity.common.NamedApiResource

class PokemonSpeciesGender(
    /**
     * The chance of this Pokémon being female, in eighths; or -1 for genderless.
     */
    val rate: Number,

    /**
     * A Pokémon species that can be the referenced gender.
     * @see PokemonSpecies
     * @see NamedApiResource
     */
    val pokemon_species: NamedApiResource
)