package entity.items

import com.fasterxml.jackson.annotation.JsonProperty
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
    @JsonProperty("id")
    val id: Number,

    /**
     * The name for this resource.
     */
    @JsonProperty("name")
    val name: String,

    /**
     * The result of this fling effect listed in different languages.
     * @see Effect
     */
    @JsonProperty("effect_entries")
    val effectEntries: List<Effect>,

    /**
     * A list of items that have this fling effect.
     * @see NamedApiResource
     * @see Item
     */
    @JsonProperty("items")
    val items: List<NamedApiResource>
) {
    override fun toString(): String {
        return "ItemFlingEffect(id=$id, name='$name', effectEntries=$effectEntries, items=$items)"
    }
}
