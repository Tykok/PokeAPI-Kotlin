package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource

public data class PokemonSpeciesGender(
    /**
     * The chance of this Pokémon being female, in eighths; or -1 for genderless.
     */
    val rate: Int,
    /**
     * A Pokémon species that can be the referenced gender.
     * @see PokemonSpecies
     * @see NamedApiResource
     */
    val pokemonSpecies: NamedApiResource<PokemonSpecies>
) : PokeApiObject
