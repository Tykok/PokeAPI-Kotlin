package entity.items

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.Name
import entity.common.NamedApiResource

/**
 * Item categories determine where items will be placed in the players bag.
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Item">Bulbapedia documentation</a>
 * @see <a href="https://pokeapi.co/docs/v2#item-categories">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class ItemCategory(

    /**
     * The identifier for this resource.
     */
    @JsonProperty("id")
    val id: Number,

    /**
     * The name for this resource
     */
    @JsonProperty("name")
    val name: String,

    /**
     * A list of items that have this attribute.
     * @see NamedApiResource
     * @see Item
     */
    @JsonProperty("items")
    val items: List<NamedApiResource<Item>>,

    /**
     * The name of this item attribute listed in different languages.
     * @see Name
     */
    @JsonProperty("names")
    val names: List<Name>,

    /**
     * The pocket items in this category would be put in.
     * @see NamedApiResource
     * @see ItemPocket
     */
    @JsonProperty("pocket")
    val pocket: NamedApiResource<ItemPocket>
) {
    override fun toString(): String {
        return "ItemCategory(id=$id, name='$name', items=$items, names=$names, pocket=$pocket)"
    }
}
