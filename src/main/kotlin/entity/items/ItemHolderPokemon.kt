package entity.items

import entity.common.NamedApiResource
import entity.pokemon.Pokemon

/**
 * @see <a href="https://pokeapi.co/docs/v2#itemholderpokemon">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class ItemHolderPokemon (
    /**
     * The Pokémon that holds this item.
     * @see NamedApiResource
     * @see Pokemon
     */
    val pokemon: NamedApiResource,

    /**
     * The details for the version that this item is held in by the Pokémon.
     * @see ItemHolderPokemonVersionDetail
     */
    val version_details: ItemHolderPokemonVersionDetail
)