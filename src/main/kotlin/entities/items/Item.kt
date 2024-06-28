package entities.items

import com.fasterxml.jackson.annotation.JsonProperty
import entities.PokeApiObject
import entities.common.APIResource
import entities.common.GenerationGameIndex
import entities.common.MachineVersionDetail
import entities.common.Name
import entities.common.NamedApiResource
import entities.common.VerboseEffect
import entities.common.VersionGroupFlavorText
import entities.evolution.EvolutionChain

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
    @JsonProperty("id")
    val id: Number,
    /**
     * The name for this resource.
     */
    @JsonProperty("name")
    val name: String,
    /**
     * The price of this item in stores.
     */
    @JsonProperty("cost")
    val cost: Int,
    /**
     * The power of the move Fling when used with this item.
     */
    @JsonProperty("fling_power")
    val flingPower: Number?,
    /**
     * The effect of the move Fling when used with this item.
     * @see NamedApiResource
     * @see ItemFlingEffect
     */
    @JsonProperty("fling_effect")
    val flingEffect: NamedApiResource<ItemFlingEffect>?,
    /**
     * A list of attributes this item has.
     * @see NamedApiResource
     * @see ItemAttribute
     */
    @JsonProperty("attributes")
    val attributes: List<NamedApiResource<ItemAttribute>>,
    /**
     * The category of items this item falls into.
     * @see NamedApiResource
     * @see ItemCategory
     */
    @JsonProperty("category")
    val category: NamedApiResource<ItemCategory>,
    /**
     * The effect of this ability listed in different languages.
     * @see VerboseEffect
     */
    @JsonProperty("effect_entries")
    val effectEntries: List<VerboseEffect>,
    /**
     * The flavor text of this ability listed in different languages.
     * @see VersionGroupFlavorText
     */
    @JsonProperty("flavor_text_entries")
    val flavorTextEntries: List<VersionGroupFlavorText>,
    /**
     * A list of game indices relevent to this item by generation.
     * @see GenerationGameIndex
     */
    @JsonProperty("game_indices")
    val gameIndices: List<GenerationGameIndex>,
    /**
     * The name of this item listed in different languages.
     * @see Name
     */
    @JsonProperty("names")
    val names: List<Name>,
    /**
     * A set of sprites used to depict this item in the game.
     * @see ItemSprites
     */
    @JsonProperty("sprites")
    val sprites: ItemSprites?,
    /**
     * A list of Pokémon that might be found in the wild holding this item.
     * @see ItemHolderPokemon
     */
    @JsonProperty("held_by_pokemon")
    val heldByPokemon: List<ItemHolderPokemon>,
    /**
     * An evolution chain this item requires to produce a bay during mating.
     * @see APIResource
     * @see EvolutionChain
     */
    @JsonProperty("baby_trigger_for")
    val babyTriggerFor: APIResource?,
    /**
     * A list of the machines related to this item.
     * @see MachineVersionDetail
     */
    @JsonProperty("machines")
    val machines: List<MachineVersionDetail>
) : PokeApiObject
