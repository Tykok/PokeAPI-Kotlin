package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource

public data class PokemonStat(
    /**
     * The stat the Pokémon has.
     * @see Stat
     */
    val stat: NamedApiResource<Stat>,
    /**
     * The effort points (EV) the Pokémon has in the stat.
     */
    val effort: Int,
    /**
     * The base value of the stat.
     */
    val baseStat: Int
) : PokeApiObject
