package entity.common

import com.fasterxml.jackson.annotation.JsonProperty
import entity.games.Generation

/**
 * @see <a href="https://pokeapi.co/docs/v2#generationgameindex">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class GenerationGameIndex(

    /**
     * The internal id of an API resource within game data.
     */
    @JsonProperty("game_index")
    val gameIndex: Number,

    /**
     * The generation relevent to this game index.
     * @see NamedApiResource
     * @see Generation
     */
    @JsonProperty("generation")
    val generation: NamedApiResource<Generation>
)
