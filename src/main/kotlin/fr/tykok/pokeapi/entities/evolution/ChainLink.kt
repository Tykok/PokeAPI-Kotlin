package fr.tykok.pokeapi.entities.evolution

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.pokemon.PokemonSpecies

/**
 * ChainLink, used to link a Pokemon with this evolution. With this classe you can have
 * many data about the Pokemon and is evolution.
 *
 * With the property `evolves_to` you can have a real history of the Pokemon.
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Evolution">Bulbapedia documentation</a>
 * @see <a href="https://pokeapi.co/docs/v2#evolution-chains">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class ChainLink(
    /**
     * Whether or not this link is for a baby Pokémon. This would only ever be true on the base link.
     */
    val isBaby: Boolean,
    /**
     * The Pokémon species at this point in the evolution chain.
     * @see NamedApiResource
     * @see PokemonSpecies
     */
    val species: NamedApiResource<PokemonSpecies>,
    /**
     * All details regarding the specific details of the referenced Pokémon species evolution.
     * @see EvolutionDetail
     */
    val evolutionDetails: List<EvolutionDetail>,
    /**
     * A List of chain objects.
     * @see ChainLink
     */
    val evolvesTo: List<ChainLink>
) : PokeApiObject
