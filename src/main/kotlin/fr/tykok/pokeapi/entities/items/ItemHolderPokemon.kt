package fr.tykok.pokeapi.entities.items

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.pokemon.Pokemon

/**
 * @see <a href="https://pokeapi.co/docs/v2#itemholderpokemon">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class ItemHolderPokemon(
    /**
     * The Pokémon that holds this item.
     * @see NamedApiResource
     * @see Pokemon
     */
    @JsonProperty("pokemon")
    val pokemon: NamedApiResource<Pokemon>,
    /**
     * The details for the version that this item is held in by the Pokémon.
     * @see ItemHolderPokemonVersionDetail
     */
    @JsonProperty("version_details")
    val versionDetails: ItemHolderPokemonVersionDetail
)
