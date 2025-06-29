package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource

data class PokemonAbility(
    /**
     *
     * Whether or not this is a hidden ability.
     */
    val isHidden: Boolean,
    /**
     * The slot this ability occupies in this Pokémon species.
     */
    val slot: Int,
    /**
     * The ability the Pokémon may have.
     * @see Ability
     */
    val ability: NamedApiResource<Ability>
) : PokeApiObject
