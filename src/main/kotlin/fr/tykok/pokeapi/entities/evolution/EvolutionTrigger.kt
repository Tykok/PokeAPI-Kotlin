package fr.tykok.pokeapi.entities.evolution
import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.pokemon.PokemonSpecies

/**
 * Evolution triggers are the events and conditions that cause a Pokémon to evolve.
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Methods_of_evolution">Bulbapedia documentation</a>
 * @see <a href="https://pokeapi.co/docs/v2#evolution-triggers">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class EvolutionTrigger(
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
     * @see NamedApiResource
     * @see Name
     */
    val names: List<Name>,
    /**
     * A list of pokemon species that result from this evolution trigger.
     * @see NamedApiResource
     * @see PokemonSpecies
     */
    val pokemonSpecies: List<NamedApiResource<PokemonSpecies>>
) : PokeApiEndpointReference
