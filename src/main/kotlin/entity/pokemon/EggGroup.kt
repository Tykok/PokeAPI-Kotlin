package entity.pokemon

import entity.common.Name
import entity.common.NamedApiResource

class EggGroup(
    /**
     * The identifier for this resource.
     */
    val id: Number,

    /**
     * The name for this resource.
     */
    val name: String,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    val names: Array<Name>,

    /**
     * A list of all Pokémon species that are members of this egg group.
     * @see PokemonSpecies
     * @see NamedApiResource
     */
    val pokemon_species: Array<NamedApiResource>
)