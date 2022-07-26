package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource
import entity.common.VersionGameIndex

class Pokemon(
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
     * The base experience gained for defeating this Pokémon.
     */
    @JsonProperty("base_experience")
    val baseExperience: Number,

    /**
     * The height of this Pokémon in decimetres.
     */
    @JsonProperty("height")
    val height: Number,

    /**
     * Set for exactly one Pokémon used as the default for each species.
     */
    @JsonProperty("is_default")
    val isDefault: Boolean,

    /**
     * Order for sorting. Almost national order, except families are grouped together.
     */
    @JsonProperty("order")
    val order: Number,

    /**
     * The weight of this Pokémon in hectograms.
     */
    @JsonProperty("weight")
    val weight: Number,

    /**
     * A list of abilities this Pokémon could potentially have.
     */
    @JsonProperty("abilities")
    val abilities: List<PokemonAbility>,

    /**
     * A list of forms this Pokémon can take on.
     *  @see PokemonForm
     */
    @JsonProperty("forms")
    val forms: List<NamedApiResource>,

    /**
     * A list of game indices relevent to Pokémon item by generation.
     */
    @JsonProperty("game_indices")
    val gameIndices: List<VersionGameIndex>,

    /**
     * A list of items this Pokémon may be holding when encountered.
     */
    @JsonProperty("held_items")
    val heldItems: List<PokemonHeldItem>,

    /**
     * A link to a list of location areas, as well as encounter details pertaining to specific versions.
     */
    @JsonProperty("location_area_encounters")
    val locationAreaEncounters: String,

    /**
     * A list of moves along with learn methods and level details pertaining to specific version groups.
     */
    @JsonProperty("moves")
    val moves: List<PokemonMove>,

    /**
     * A list of details showing types this pokémon had in previous generations
     */
    @JsonProperty("past_types")
    val pastTypes: List<PokemonTypePast>,

    /**
     * A set of sprites used to depict this Pokémon in the game. A visual representation of the various sprites can be found at PokeAPI/sprites
     */
    @JsonProperty("sprites")
    val sprites: PokemonSprites,

    /**
     * The species this Pokémon belongs to.
     * @see PokemonSpecies
     */
    @JsonProperty("species")
    val species: NamedApiResource,

    /**
     * A list of base stat values for this Pokémon.
     */

    @JsonProperty("stats")
    val stats: List<PokemonStat>,

    /**
     * A list of details showing types this Pokémon has.
     */
    @JsonProperty("types")
    val types: List<PokemonType>
) {
    override fun toString(): String {
        return "Pokemon(id=$id, name='$name', baseExperience=$baseExperience, height=$height, isDefault=$isDefault, order=$order, " +
            "weight=$weight, abilities=$abilities, forms=$forms, gameIndices=$gameIndices, heldItems=$heldItems, " +
            "locationAreaEncounters='$locationAreaEncounters', moves=$moves, pastTypes=$pastTypes, sprites=$sprites, " +
            "species=$species, stats=$stats, types=$types)"
    }
}
