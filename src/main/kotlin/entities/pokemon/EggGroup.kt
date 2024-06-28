package entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entities.PokeApiObject
import entities.common.Name
import entities.common.NamedApiResource

data class EggGroup(
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
     * The name of this resource listed in different languages.
     * @see Name
     */
    @JsonProperty("names")
    val names: List<Name>,
    /**
     * A list of all Pokémon species that are members of this egg group.
     * @see PokemonSpecies
     * @see NamedApiResource
     */
    @JsonProperty("pokemon_species")
    val pokemonSpecies: List<NamedApiResource<PokemonSpecies>>
) : PokeApiObject
