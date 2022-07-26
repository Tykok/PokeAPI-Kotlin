package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource

class PokemonMove(
    /**
     * The move the Pokémon can learn.
     * @see Move
     */
    @JsonProperty("move")
    val move: NamedApiResource,

    @JsonProperty("version_group_details")
    val versionGroupDetails: Array<PokemonMoveVersion>,
)
