package fr.tykok.pokeapi.entities.items
import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.Description
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource

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
data class ItemAttribute(
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
    val items: List<NamedApiResource<Item>>,
    /**
     * The name of this item attribute listed in different languages.
     * @see Name
     */
    val names: List<Name>,
    /**
     * The description of this item attribute listed in different languages.
     * @see Description
     */
    val descriptions: List<Description>
) : PokeApiEndpointReference
