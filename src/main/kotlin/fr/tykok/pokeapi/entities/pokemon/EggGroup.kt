package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.annotation.Endpoint
import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource

@Endpoint("egg-group")
data class EggGroup(
    /**
     * The identifier for this resource.
     */
    val id: Int,
    /**
     * The name for this resource.
     */
    val name: String,
    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    val names: List<Name>,
    /**
     * A list of all Pokémon species that are members of this egg group.
     * @see PokemonSpecies
     * @see NamedApiResource
     */
    val pokemonSpecies: List<NamedApiResource<PokemonSpecies>>
) : PokeApiEndpointReference
