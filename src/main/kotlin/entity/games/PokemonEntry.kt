package entity.games

import entity.common.NamedApiResource
import entity.pokemon.PokemonSpecies
/**
 * @see <a href="https://pokeapi.co/docs/v2#pokedexes">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class PokemonEntry(

    /**
     * The index of this Pokémon species entry within the Pokédex.
     */
    val entry_number: Number,

    /**
     * The Pokémon species being encountered.
     * @see NamedApiResource
     * @see PokemonSpecies
     */
    val pokemon_species: NamedApiResource,
)
