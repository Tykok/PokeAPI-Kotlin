package fr.tykok.pokeapi.entities.items
import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.Effect
import fr.tykok.pokeapi.entities.common.NamedApiResource

/**
 * The various effects of the move "Fling" when used with different items.
 *
 * @see <a href="https://pokeapi.co/docs/v2#item-fling-effects">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class ItemFlingEffect(
    /**
     * The identifier for this resource.
     */
    val id: Number,
    /**
     * The name for this resource.
     */
    val name: String,
    /**
     * The result of this fling effect listed in different languages.
     * @see Effect
     */
    val effectEntries: List<Effect>,
    /**
     * A list of items that have this fling effect.
     * @see NamedApiResource
     * @see Item
     */
    val items: List<NamedApiResource<Item>>
) : PokeApiEndpointReference
