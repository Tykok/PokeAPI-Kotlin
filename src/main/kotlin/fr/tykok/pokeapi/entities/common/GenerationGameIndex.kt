package fr.tykok.pokeapi.entities.common

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.games.Generation

/**
 * @see <a href="https://pokeapi.co/docs/v2#generationgameindex">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class GenerationGameIndex(
    /**
     * The internal id of an API resource within game data.
     */
    val gameIndex: Number,
    /**
     * The generation relevent to this game index.
     * @see NamedApiResource
     * @see Generation
     */
    val generation: NamedApiResource<Generation>
) : PokeApiObject
