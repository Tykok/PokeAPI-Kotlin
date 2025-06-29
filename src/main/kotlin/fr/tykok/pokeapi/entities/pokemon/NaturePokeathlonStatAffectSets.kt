package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiObject

data class NaturePokeathlonStatAffectSets(
    /**
     * A list of natures and how they change the referenced Pokéathlon stat.
     */
    val increase: List<NaturePokeathlonStatAffect>,
    /**
     * A list of natures and how they change the referenced Pokéathlon stat.
     */
    val decrease: List<NaturePokeathlonStatAffect>
) : PokeApiObject
