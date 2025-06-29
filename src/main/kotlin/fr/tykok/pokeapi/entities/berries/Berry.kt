package fr.tykok.pokeapi.entities.berries

import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.items.Item
import fr.tykok.pokeapi.entities.pokemon.Type

/**
 * Berries (Japanese: きのみ Tree Fruit) are small, juicy, fleshy fruit.
 * As in the real world, a large variety exists in the Pokémon world, with a large range of flavors and effects.
 * @link https://bulbapedia.bulbagarden.net/wiki/Berry
 * @author Tykok
 * @version 1.0.0
 * @since 2022-07-27
 */
data class Berry(
    /**
     * The identifier of the resource
     */

    val id: Number,
    /**
     * The name of the resource
     */

    val name: String,
    /**
     * Time it takes the tree to grow one stage, in hours.
     * Berry trees go through four of these growth stages before they can be picked.
     */

    val growthTime: Number,
    /**
     * The maximum number of these berries that can grow on one tree in Generation IV.
     */

    val maxHarvest: Number,
    /**
     * The power of the move "Natural Gift" when used with this Berry.
     */

    val naturalGiftPower: Number,
    /**
     * The size of this Berry, in millimeters.
     */

    val size: Number,
    /**
     * The smoothness of this Berry, used in making Pokéblocks or Poffins.
     */

    val smoothness: Number,
    /**
     * The speed at which this Berry dries out the soil as it grows.
     * A higher rate means the soil dries more quickly.
     */

    val soilDryness: Number,
    /**
     * The firmness of this berry, used in making Pokéblocks or Poffins.
     * @see NamedApiResource
     * @see BerryFirmness
     */

    val firmness: NamedApiResource<BerryFirmness>,
    /**
     * A list of references to each flavor a berry can have and the potency of each of those flavors in regard to this berry.
     * @see BerryFlavorMap
     */

    val flavors: List<BerryFlavorMap>,
    /**
     * Berries are actually items. This is a reference to the item specific data for this berry.
     * @see NamedApiResource
     * @see Item
     */

    val item: NamedApiResource<Item>,
    /**
     * The type inherited by "Natural Gift" when used with this Berry.
     * @see NamedApiResource
     * @see Type
     */

    val naturalGiftType: NamedApiResource<Type>
) : PokeApiEndpointReference
