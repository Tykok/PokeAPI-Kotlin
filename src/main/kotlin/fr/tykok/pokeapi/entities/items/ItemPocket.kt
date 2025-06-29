package fr.tykok.pokeapi.entities.items
import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource

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
data class ItemPocket(
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
    val categories: List<NamedApiResource<ItemCategory>>,
    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    val names: List<Name>
) : PokeApiEndpointReference
