package entity.items

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
    val id: Number,

    /**
     * The name for this resource.
     */
    val name: String,

    /**
     * A list of item categories that are relevant to this item pocket.
     * @see NamedApiResource
     * @see ItemCategory
     */
    val categories: NamedApiResource,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    val names: Array<Name>
)