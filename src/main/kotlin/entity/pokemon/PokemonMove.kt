package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource
import entity.moves.Move

class PokemonMove(
    /**
     * The move the Pokémon can learn.
     * @see Move
     */
    @JsonProperty("move")
    val move: NamedApiResource<Move>,

    @JsonProperty("version_group_details")
    val versionGroupDetails: Array<PokemonMoveVersion>
)
