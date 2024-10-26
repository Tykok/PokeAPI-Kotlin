package fr.tykok.pokeapi.entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.games.VersionGroup
import fr.tykok.pokeapi.entities.moves.MoveLearnMethod

data class PokemonMoveVersion(
    /**
     * The method by which the move is learned.
     * @see MoveLearnMethod
     */
    @JsonProperty("move_learn_method") val moveLearnMethod: NamedApiResource<MoveLearnMethod>,
    /**
     * The version group in which the move is learned.
     * @see VersionGroup
     */
    @JsonProperty("version_group") val versionGroup: NamedApiResource<VersionGroup>,
    /**
     * The minimum level to learn the move. Specified for each different move learn method.
     */
    @JsonProperty("level_learned_at") val levelLearnedAt: Number
)
