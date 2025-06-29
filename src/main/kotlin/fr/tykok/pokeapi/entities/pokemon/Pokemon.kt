package fr.tykok.pokeapi.entities.pokemon
import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.common.VersionGameIndex

data class Pokemon(
    /**
     * The identifier for this resource.
     */
    val id: Number,
    /**
     * The name for this resource.
     */
    val name: String,
    /**
     * The base experience gained for defeating this Pokémon.
     */
    val baseExperience: Number,
    /**
     * The height of this Pokémon in decimetres.
     */
    val height: Number,
    /**
     * Set for exactly one Pokémon used as the default for each species.
     */
    val isDefault: Boolean,
    /**
     * Order for sorting. Almost national order, except families are grouped together.
     */
    val order: Number,
    /**
     * The weight of this Pokémon in hectograms.
     */
    val weight: Number,
    /**
     * A list of abilities this Pokémon could potentially have.
     */
    val abilities: List<PokemonAbility>,
    /**
     * A list of forms this Pokémon can take on.
     *  @see PokemonForm
     */
    val forms: List<NamedApiResource<PokemonForm>>,
    /**
     * A list of game indices relevent to Pokémon item by generation.
     */
    val gameIndices: List<VersionGameIndex>,
    /**
     * A list of items this Pokémon may be holding when encountered.
     */
    val heldItems: List<PokemonHeldItem>,
    /**
     * A link to a list of location areas, as well as encounter details pertaining to specific versions.
     */
    val locationAreaEncounters: String,
    /**
     * A list of moves along with learn methods and level details pertaining to specific version groups.
     */
    val moves: List<PokemonMove>,
    /**
     * A list of details showing types this pokémon had in previous generations
     */
    val pastTypes: List<PokemonTypePast>,
    /**
     * A set of sprites used to depict this Pokémon in the game. A visual representation of the various sprites can be found at PokeAPI/sprites
     */
    val sprites: PokemonSprites,
    /**
     * The species this Pokémon belongs to.
     * @see PokemonSpecies
     */
    val species: NamedApiResource<PokemonSpecies>,
    /**
     * A list of base stat values for this Pokémon.
     */

    val stats: List<PokemonStat>,
    /**
     * A list of details showing types this Pokémon has.
     */
    val types: List<PokemonType>
) : PokeApiEndpointReference
