package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource

data class NaturePokeathlonStatAffect(
    /**
     *  The maximum amount of change to the referenced Pokéathlon stat.
     */
    val maxChange: Number,
    /**
     * The nature causing the change.
     * @see Nature
     */
    val nature: NamedApiResource<Nature>
) : PokeApiObject
