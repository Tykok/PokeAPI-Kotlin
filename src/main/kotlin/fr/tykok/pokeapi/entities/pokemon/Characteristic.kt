package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.annotation.Endpoint
import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.Description
import fr.tykok.pokeapi.entities.common.NamedApiResource

@Endpoint("characteristic")
public data class Characteristic(
    /**
     * The identifier for this resource.
     */
    val id: Int,
    /**
     * The remainder of the highest stat/IV divided by 5.
     */
    val geneModulo: Int,
    /**
     * The possible values of the highest stat that would result in a Pokémon recieving this characteristic when divided by 5.
     */
    val possibleValues: List<Number>,
    val descriptions: List<Description>,
    /**
     * The stat which is affected by this characteristic.
     */
    val highestStat: NamedApiResource<Stat>
) : PokeApiEndpointReference
