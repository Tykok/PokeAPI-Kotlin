package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.moves.Move

public data class PokemonMove(
    /**
     * The move the Pokémon can learn.
     * @see Move
     */
    val move: NamedApiResource<Move>,
    val versionGroupDetails: List<PokemonMoveVersion>
) : PokeApiObject
