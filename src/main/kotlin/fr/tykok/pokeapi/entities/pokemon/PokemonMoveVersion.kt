package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.games.VersionGroup
import fr.tykok.pokeapi.entities.moves.MoveLearnMethod

data class PokemonMoveVersion(
    /**
     * The method by which the move is learned.
     * @see MoveLearnMethod
     */
    val moveLearnMethod: NamedApiResource<MoveLearnMethod>,
    /**
     * The version group in which the move is learned.
     * @see VersionGroup
     */
    val versionGroup: NamedApiResource<VersionGroup>,
    /**
     * The minimum level to learn the move. Specified for each different move learn method.
     */
    val levelLearnedAt: Number
) : PokeApiObject
