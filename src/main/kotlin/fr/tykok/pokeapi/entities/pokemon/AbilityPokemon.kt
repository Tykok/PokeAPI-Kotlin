package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource

data class AbilityPokemon(
    /**
     * Whether or not this a hidden ability for the referenced Pokémon.
     */
    val isHidden: Boolean,
    /**
     * Pokémon have 3 ability 'slots' which hold references to possible abilities they could have. This is the slot of this ability for the referenced pokemon.
     */
    val slot: Number,
    /**
     * The Pokémon this ability could belong to.
     * @see Pokemon
     * @see NamedApiResource
     */
    val pokemon: NamedApiResource<Pokemon>
) : PokeApiObject
