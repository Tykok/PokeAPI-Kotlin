package entity.pokemon

import entity.common.NamedApiResource

class AbilityPokemon(

    /**
     * Whether or not this a hidden ability for the referenced Pokémon.
     */
    val is_hidden: Boolean,

    /**
     * Pokémon have 3 ability 'slots' which hold references to possible abilities they could have. This is the slot of this ability for the referenced pokemon.
     */
    val slot: Number,

    /**
     * The Pokémon this ability could belong to.
     * @see Pokemon
     * @see NamedApiResource
     */
    val pokemon: NamedApiResource
)