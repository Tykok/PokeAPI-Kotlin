package fr.tykok.pokeapi.entities.items
import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.APIResource
import fr.tykok.pokeapi.entities.common.GenerationGameIndex
import fr.tykok.pokeapi.entities.common.MachineVersionDetail
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.common.VerboseEffect
import fr.tykok.pokeapi.entities.common.VersionGroupFlavorText
import fr.tykok.pokeapi.entities.evolution.EvolutionChain

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
data class Item(
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
    val cost: Int,
    /**
     * The power of the move Fling when used with this item.
     */
    val flingPower: Number?,
    /**
     * The effect of the move Fling when used with this item.
     * @see NamedApiResource
     * @see ItemFlingEffect
     */
    val flingEffect: NamedApiResource<ItemFlingEffect>?,
    /**
     * A list of attributes this item has.
     * @see NamedApiResource
     * @see ItemAttribute
     */
    val attributes: List<NamedApiResource<ItemAttribute>>,
    /**
     * The category of items this item falls into.
     * @see NamedApiResource
     * @see ItemCategory
     */
    val category: NamedApiResource<ItemCategory>,
    /**
     * The effect of this ability listed in different languages.
     * @see VerboseEffect
     */
    val effectEntries: List<VerboseEffect>,
    /**
     * The flavor text of this ability listed in different languages.
     * @see VersionGroupFlavorText
     */
    val flavorTextEntries: List<VersionGroupFlavorText>,
    /**
     * A list of game indices relevent to this item by generation.
     * @see GenerationGameIndex
     */
    val gameIndices: List<GenerationGameIndex>,
    /**
     * The name of this item listed in different languages.
     * @see Name
     */
    val names: List<Name>,
    /**
     * A set of sprites used to depict this item in the game.
     * @see ItemSprites
     */
    val sprites: ItemSprites?,
    /**
     * A list of Pokémon that might be found in the wild holding this item.
     * @see ItemHolderPokemon
     */
    val heldByPokemon: List<ItemHolderPokemon>,
    /**
     * An evolution chain this item requires to produce a bay during mating.
     * @see APIResource
     * @see EvolutionChain
     */
    val babyTriggerFor: APIResource?,
    /**
     * A list of the machines related to this item.
     * @see MachineVersionDetail
     */
    val machines: List<MachineVersionDetail>
) : PokeApiEndpointReference
