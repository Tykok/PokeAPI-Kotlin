package entity.evolution

import com.sun.org.apache.xpath.internal.operations.Bool
import entity.NamedApiResource
import entity.items.Item
import entity.moves.Move
import entity.pokemon.Type
import entity.pokemon.PokemonSpecies
import entity.locations.Location

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
class EvolutionDetail(

    /**
     * The item required to cause evolution this into Pokémon species.
     * @see NamedApiResource
     * @see Item
     */
    val item: NamedApiResource,

    /**
     * The type of event that triggers evolution into this Pokémon species.
     * @see NamedApiResource
     * @see EvolutionTrigger
     */
    val trigger: NamedApiResource,

    /**
     * The id of the gender of the evolving Pokémon species must be in order to evolve into this Pokémon species.
     */
    val gender: Number,

    /**
     * The item the evolving Pokémon species must be holding during the evolution trigger event to evolve into this Pokémon species.
     * @see NamedApiResource
     * @see Item
     */
    val held_item: NamedApiResource,

    /**
     * The move that must be known by the evolving Pokémon species during the evolution trigger event in order to evolve into this Pokémon species.
     * @see NamedApiResource
     * @see Move
     */
    val known_move: NamedApiResource,

    /**
     * The evolving Pokémon species must know a move with this type during the evolution trigger event in order to evolve into this Pokémon species.
     * @see NamedApiResource
     * @see Type
     */
    val known_move_type: NamedApiResource,

    /**
     * The location the evolution must be triggered at.
     * @see NamedApiResource
     * @see Location
     */
    val location: NamedApiResource,

    /**
     * The minimum required level of the evolving Pokémon species to evolve into this Pokémon species.
     */
    val min_level: Number,

    /**
     * The minimum required level of happiness the evolving Pokémon species to evolve into this Pokémon species.
     */
    val min_happiness: Number,

    /**
     * The minimum required level of beauty the evolving Pokémon species to evolve into this Pokémon species.
     */
    val min_beauty: Number,

    /**
     * The minimum required level of affection the evolving Pokémon species to evolve into this Pokémon species.
     */
    val min_affection: Number,

    /**
     * Whether or not it must be raining in the overworld to cause evolution this Pokémon species.
     */
    val needs_overworld_rain: Boolean,

    /**
     * The Pokémon species that must be in the players party in order for the evolving Pokémon species to evolve into this Pokémon species.
     * @see NamedApiResource
     * @see PokemonSpecies
     */
    val party_species: Number,

    /**
     * The player must have a Pokémon of this type in their party during the evolution trigger event in order for
     * the evolving Pokémon species to evolve into this Pokémon species.
     * @see NamedApiResource
     * @see Type
     */
    val party_type: NamedApiResource,

    /**
     * The required relation between the Pokémon's Attack and Defense stats. 1 means Attack > Defense. 0 means Attack = Defense. -1 means Attack < Defense.
     */
    val relative_physical_stats: Number,

    /**
     * The required time of day. Day or night.
     */
    val time_of_day: String,

    /**
     * Pokémon species for which this one must be traded.
     * @see NamedApiResource
     * @see PokemonSpecies
     */
    val trade_species: NamedApiResource,

    /**
     * Whether or not the 3DS needs to be turned upside-down as this Pokémon levels up.
     */
    val turn_upside_down: Boolean
)