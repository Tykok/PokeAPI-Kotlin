package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.Name

data class PokeathlonStat(
    /**
     * The identifier for this resource.
     */
    val id: Number,
    /**
     * The name for this resource.
     */
    val name: String,
    /**
     * The name of this resource listed in different languages.
     */
    val names: List<Name>,
    /**
     * A detail of natures which affect this Pokéathlon stat positively or negatively.
     */
    val affectingNatures: NaturePokeathlonStatAffectSets
) : PokeApiEndpointReference
