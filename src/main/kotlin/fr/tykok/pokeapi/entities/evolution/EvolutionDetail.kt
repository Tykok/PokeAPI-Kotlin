package fr.tykok.pokeapi.entities.evolution

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.items.Item
import fr.tykok.pokeapi.entities.locations.Location
import fr.tykok.pokeapi.entities.moves.Move
import fr.tykok.pokeapi.entities.pokemon.PokemonSpecies
import fr.tykok.pokeapi.entities.pokemon.Type

/**
 * EvolutionDetail define all possibility to make possible the evolution of the Pokemon.
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Evolution">Bulbapedia documentation</a>
 * @see <a href="https://pokeapi.co/docs/v2#evolution-chains">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class EvolutionDetail(
    /**
     * The item required to cause evolution this into Pokémon species.
     * @see NamedApiResource
     * @see Item
     */
    val item: NamedApiResource<Item>?,
    /**
     * The type of event that triggers evolution into this Pokémon species.
     * @see NamedApiResource
     * @see EvolutionTrigger
     */
    val trigger: NamedApiResource<EvolutionTrigger>?,
    /**
     * The id of the gender of the evolving Pokémon species must be in order to evolve into this Pokémon species.
     */
    val gender: Number?,
    /**
     * The item the evolving Pokémon species must be holding during the evolution trigger event to evolve into this Pokémon species.
     * @see NamedApiResource
     * @see Item
     */
    val heldItem: NamedApiResource<Item>?,
    /**
     * The move that must be known by the evolving Pokémon species during the evolution trigger event in order to evolve into this Pokémon species.
     * @see NamedApiResource
     * @see Move
     */
    val knownMove: NamedApiResource<Move>?,
    /**
     * The evolving Pokémon species must know a move with this type during the evolution trigger event in order to evolve into this Pokémon species.
     * @see NamedApiResource
     * @see Type
     */
    val knownMoveType: NamedApiResource<Type>?,
    /**
     * The location the evolution must be triggered at.
     * @see NamedApiResource
     * @see Location
     */
    val location: NamedApiResource<Location>?,
    /**
     * The minimum required level of the evolving Pokémon species to evolve into this Pokémon species.
     */
    val minLevel: Number,
    /**
     * The minimum required level of happiness the evolving Pokémon species to evolve into this Pokémon species.
     */
    val minHappiness: Number?,
    /**
     * The minimum required level of beauty the evolving Pokémon species to evolve into this Pokémon species.
     */
    val minBeauty: Number?,
    /**
     * The minimum required level of affection the evolving Pokémon species to evolve into this Pokémon species.
     */
    val minAffection: Number?,
    /**
     * Whether or not it must be raining in the overworld to cause evolution this Pokémon species.
     */
    val needsOverworldRain: Boolean,
    /**
     * The Pokémon species that must be in the players party in order for the evolving Pokémon species to evolve into this Pokémon species.
     * @see NamedApiResource
     * @see PokemonSpecies
     */
    val partySpecies: Number?,
    /**
     * The player must have a Pokémon of this type in their party during the evolution trigger event in order for
     * the evolving Pokémon species to evolve into this Pokémon species.
     * @see NamedApiResource
     * @see Type
     */
    val partyType: NamedApiResource<Type>?,
    /**
     * The required relation between the Pokémon's Attack and Defense stats. 1 means Attack > Defense. 0 means Attack = Defense. -1 means Attack < Defense.
     */
    val relativePhysicalStats: Number?,
    /**
     * The required time of day. Day or night.
     */
    val timeOfDay: String,
    /**
     * Pokémon species for which this one must be traded.
     * @see NamedApiResource
     * @see PokemonSpecies
     */
    val tradeSpecies: NamedApiResource<PokemonSpecies>?,
    /**
     * Whether or not the 3DS needs to be turned upside-down as this Pokémon levels up.
     */
    val turnUpsideDown: Boolean
) : PokeApiObject
