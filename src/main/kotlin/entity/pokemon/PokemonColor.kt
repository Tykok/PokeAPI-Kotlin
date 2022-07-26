package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.Name
import entity.common.NamedApiResource

/**
 * A class representing the details of a Pokémon color.
 *
 * @property id The identifier for this resource.
 * @property name The name for this resource.
 * @property names The name of this resource listed in different languages.
 * @property pokemonSpecies A list of the Pokémon species that have this color.
 */
class PokemonColor(

    @JsonProperty("id")
    val id: Int,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("names")
    val names: List<Name>,

    @JsonProperty("pokemon_species")
    val pokemonSpecies: List<NamedApiResource>
) {
    override fun toString(): String {
        return "PokemonColor(id=$id, name='$name', names=$names, pokemonSpecies=$pokemonSpecies"
    }
}
