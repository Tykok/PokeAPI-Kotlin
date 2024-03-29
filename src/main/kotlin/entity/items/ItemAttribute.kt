package entity.items

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.Description
import entity.common.Name
import entity.common.NamedApiResource

/**
 * Item attributes define particular aspects of items, e.g. "usable in battle" or "consumable".
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Item">Bulbapedia documentation</a>
 * @see <a href="https://pokeapi.co/docs/v2#item-attributes">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class ItemAttribute(

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
    val items: Array<NamedApiResource<Item>>,

    /**
     * The name of this item attribute listed in different languages.
     * @see Name
     */
    @JsonProperty("names")
    val names: Array<Name>,

    /**
     * The description of this item attribute listed in different languages.
     * @see Description
     */
    @JsonProperty("descriptions")
    val descriptions: Array<Description>
) {
    override fun toString(): String {
        return "ItemAttribute(id=$id, name='$name', items=$items, names=$names, descriptions=$descriptions)"
    }
}
