package entity.items

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.Name
import entity.common.NamedApiResource

/**
 * Pockets within the players bag used for storing items by category.
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Item">Bulbapedia documentation</a>
 * @see <a href="https://pokeapi.co/docs/v2#item-pockets">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class ItemPocket(

    /**
     * The identifier for this resource.
     */
    @JsonProperty("id")
    val id: Number,

    /**
     * The name for this resource.
     */
    @JsonProperty("name")
    val name: String,

    /**
     * A list of item categories that are relevant to this item pocket.
     * @see NamedApiResource
     * @see ItemCategory
     */
    @JsonProperty("categories")
    val categories: List<NamedApiResource<ItemCategory>>,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    @JsonProperty("names")
    val names: List<Name>
) {
    override fun toString(): String {
        return "ItemPocket(id=$id, name='$name', categories=$categories, names=$names)"
    }
}
