package fr.tykok.pokeapi.entities.games
import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.locations.Region
import fr.tykok.pokeapi.entities.moves.Move
import fr.tykok.pokeapi.entities.pokemon.Ability
import fr.tykok.pokeapi.entities.pokemon.PokemonSpecies
import fr.tykok.pokeapi.entities.pokemon.Type

/**
 * A generation is a grouping of the Pokémon games that separates them based on the Pokémon they include.
 * In each generation, a new set of Pokémon, Moves, Abilities and Types that did not exist in the previous generation are released.
 *
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Generation">Bulbapedia documentation</a>
 * @see <a href="https://pokeapi.co/docs/v2#generations">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class Generation(
    val id: Number,
    val name: String,
    /**
     * @see NamedApiResource
     * @see Ability
     */
    val abilities: List<NamedApiResource<Ability>>,
    val names: List<Name>,
    /**
     * @see NamedApiResource
     * @see Region
     */
    val mainRegion: NamedApiResource<Region>,
    /**
     * @see NamedApiResource
     * @see Move
     */
    val moves: List<NamedApiResource<Move>>,
    /**
     * @see NamedApiResource
     * @see PokemonSpecies
     */
    val pokemonSpecies: List<NamedApiResource<PokemonSpecies>>,
    /**
     * @see NamedApiResource
     * @see Type
     */
    val types: List<NamedApiResource<Type>>,
    /**
     * @see NamedApiResource
     * @see VersionGroup
     */
    val versionGroups: List<NamedApiResource<VersionGroup>>
) : PokeApiEndpointReference
