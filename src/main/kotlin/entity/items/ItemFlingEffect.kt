package entity.items

import entity.common.Effect
import entity.common.NamedApiResource

/**
 * The various effects of the move "Fling" when used with different items.
 *
 * @see <a href="https://pokeapi.co/docs/v2#item-fling-effects">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class ItemFlingEffect(

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
    val effect_entries: Array<Effect>,

    /**
     * A list of items that have this fling effect.
     * @see NamedApiResource
     * @see Item
     */
    val items: Array<NamedApiResource>
)