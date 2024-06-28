package entities.berries

import com.fasterxml.jackson.annotation.JsonProperty
import entities.PokeApiObject
import entities.common.NamedApiResource
import entities.items.Item
import entities.pokemon.Type

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
    @JsonProperty("id")
    val id: Number,
    /**
     * The name of the resource
     */
    @JsonProperty("name")
    val name: String,
    /**
     * Time it takes the tree to grow one stage, in hours.
     * Berry trees go through four of these growth stages before they can be picked.
     */
    @JsonProperty("growth_time")
    val growthTime: Number,
    /**
     * The maximum number of these berries that can grow on one tree in Generation IV.
     */
    @JsonProperty("max_harvest")
    val maxHarvest: Number,
    /**
     * The power of the move "Natural Gift" when used with this Berry.
     */
    @JsonProperty("natural_gift_power")
    val naturalGiftPower: Number,
    /**
     * The size of this Berry, in millimeters.
     */
    @JsonProperty("size")
    val size: Number,
    /**
     * The smoothness of this Berry, used in making Pokéblocks or Poffins.
     */
    @JsonProperty("smoothness")
    val smoothness: Number,
    /**
     * The speed at which this Berry dries out the soil as it grows.
     * A higher rate means the soil dries more quickly.
     */
    @JsonProperty("soil_dryness")
    val soilDryness: Number,
    /**
     * The firmness of this berry, used in making Pokéblocks or Poffins.
     * @see NamedApiResource
     * @see BerryFirmness
     */
    @JsonProperty("firmness")
    val firmness: NamedApiResource<BerryFirmness>,
    /**
     * A list of references to each flavor a berry can have and the potency of each of those flavors in regard to this berry.
     * @see BerryFlavorMap
     */
    @JsonProperty("flavors")
    val flavors: Array<BerryFlavorMap>,
    /**
     * Berries are actually items. This is a reference to the item specific data for this berry.
     * @see NamedApiResource
     * @see Item
     */
    @JsonProperty("item")
    val item: NamedApiResource<Item>,
    /**
     * The type inherited by "Natural Gift" when used with this Berry.
     * @see NamedApiResource
     * @see Type
     */
    @JsonProperty("natural_gift_type")
    val naturalGiftType: NamedApiResource<Type>
) : PokeApiObject
