package entity.items

import entity.common.NamedApiResource
import entity.games.Version

/**
 * @see <a href="https://pokeapi.co/docs/v2#itemholderpokemondetail">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class ItemHolderPokemonVersionDetail(

    /**
     * How often this Pokémon holds this item in this version.
     */
    val rarity: Number,

    /**
     * The version that this item is held in by the Pokémon.
     * @see NamedApiResource
     * @see Version
     */
    val version: NamedApiResource<Version>
)
