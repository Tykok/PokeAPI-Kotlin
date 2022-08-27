package entity.items

import entity.common.*
import entity.evolution.EvolutionChain

/**
 * An item is an object in the games which the player can pick up, keep in their bag, and use in some manner.
 * They have various uses, including healing, powering up, helping catch Pokémon, or to access a new area.
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Item">Bulbapedia documentation</a>
 * @see <a href="https://pokeapi.co/docs/v2#item">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class Item(

    /**
     * The identifier for this resource.
     */
    val id: Number,

    /**
     * The name for this resource.
     */
    val name: String,

    /**
     * The price of this item in stores.
     */
    val cost: Integer,

    /**
     * The power of the move Fling when used with this item.
     */
    val fling_power: Number,

    /**
     * The effect of the move Fling when used with this item.
     * @see NamedApiResource
     * @see ItemFlingEffect
     */
    val fling_effect: NamedApiResource,

    /**
     * A list of attributes this item has.
     * @see NamedApiResource
     * @see ItemAttribute
     */
    val attributes: NamedApiResource,

    /**
     * The category of items this item falls into.
     * @see NamedApiResource
     * @see ItemCategory
     */
    val category: NamedApiResource,

    /**
     * The effect of this ability listed in different languages.
     * @see VerboseEffect
     */
    val effect_entries: Array<VerboseEffect>,

    /**
     * The flavor text of this ability listed in different languages.
     * @see VersionGroupFlavorText
     */
    val flavor_text_entries: Array<VersionGroupFlavorText>,

    /**
     * A list of game indices relevent to this item by generation.
     * @see GenerationGameIndex
     */
    val game_indices: Array<GenerationGameIndex>,

    /**
     * The name of this item listed in different languages.
     * @see Name
     */
    val names: Array<Name>,

    /**
     * A set of sprites used to depict this item in the game.
     * @see ItemSprites
     */
    val sprites: ItemSprites,

    /**
     * A list of Pokémon that might be found in the wild holding this item.
     * @see ItemHolderPokemon
     */
    val held_by_pokemon: Array<ItemHolderPokemon>,

    /**
     * An evolution chain this item requires to produce a bay during mating.
     * @see APIResource
     * @see EvolutionChain
     */
    val baby_trigger_for: APIResource,

    /**
     * A list of the machines related to this item.
     * @see MachineVersionDetail
     */
    val machines: Array<MachineVersionDetail>
    )