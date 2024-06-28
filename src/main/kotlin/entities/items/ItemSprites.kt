package entities.items

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * An item is an object in the games which the player can pick up, keep in their bag, and use in some manner.
 * They have various uses, including healing, powering up, helping catch Pokémon, or to access a new area.
 *
 * @see <a href="https://pokeapi.co/docs/v2#itemsprites">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class ItemSprites(
    /**
     * The default depiction of this item.
     */
    @JsonProperty("default")
    val default: String
)
