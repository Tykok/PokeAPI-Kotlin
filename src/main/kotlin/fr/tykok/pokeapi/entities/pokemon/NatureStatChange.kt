package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource

data class NatureStatChange(
    /**
     * The amount of change.
     */
    val maxChange: Number,
    /**
     * The stat being affected.
     * @see PokeathlonStat
     * @see NamedApiResource
     */
    val pokeathlonStat: NamedApiResource<PokeathlonStat>
) : PokeApiObject
