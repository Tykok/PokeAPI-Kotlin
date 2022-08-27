package entity.items

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
    val id: Number,

    /**
     * The name for this resource
     */
    val name: String,

    /**
     * A list of items that have this attribute.
     * @see NamedApiResource
     * @see Item
     */
    val items: Array<NamedApiResource>,

    /**
     * The name of this item attribute listed in different languages.
     * @see Name
     */
    val names: Array<Name>,

    /**
     * The description of this item attribute listed in different languages.
     * @see Description
     */
    val descriptions: Array<Description>
)