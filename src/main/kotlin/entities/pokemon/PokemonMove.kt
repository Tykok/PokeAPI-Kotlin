package entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entities.common.NamedApiResource
import entities.moves.Move

data class PokemonMove(
    /**
     * The move the Pokémon can learn.
     * @see Move
     */
    @JsonProperty("move")
    val move: NamedApiResource<Move>,
    @JsonProperty("version_group_details")
    val versionGroupDetails: Array<PokemonMoveVersion>
)
