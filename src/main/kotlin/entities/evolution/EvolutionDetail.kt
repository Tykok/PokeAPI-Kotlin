package entities.evolution

import com.fasterxml.jackson.annotation.JsonProperty
import entities.common.NamedApiResource
import entities.items.Item
import entities.locations.Location
import entities.moves.Move
import entities.pokemon.PokemonSpecies
import entities.pokemon.Type

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
    @JsonProperty("item")
    val item: NamedApiResource<Item>?,
    /**
     * The type of event that triggers evolution into this Pokémon species.
     * @see NamedApiResource
     * @see EvolutionTrigger
     */
    @JsonProperty("trigger")
    val trigger: NamedApiResource<EvolutionTrigger>?,
    /**
     * The id of the gender of the evolving Pokémon species must be in order to evolve into this Pokémon species.
     */
    @JsonProperty("number")
    val gender: Number?,
    /**
     * The item the evolving Pokémon species must be holding during the evolution trigger event to evolve into this Pokémon species.
     * @see NamedApiResource
     * @see Item
     */
    @JsonProperty("held_item")
    val heldItem: NamedApiResource<Item>?,
    /**
     * The move that must be known by the evolving Pokémon species during the evolution trigger event in order to evolve into this Pokémon species.
     * @see NamedApiResource
     * @see Move
     */
    @JsonProperty("known_move")
    val knownMove: NamedApiResource<Move>?,
    /**
     * The evolving Pokémon species must know a move with this type during the evolution trigger event in order to evolve into this Pokémon species.
     * @see NamedApiResource
     * @see Type
     */
    @JsonProperty("known_move_type")
    val knownMoveType: NamedApiResource<Type>?,
    /**
     * The location the evolution must be triggered at.
     * @see NamedApiResource
     * @see Location
     */
    @JsonProperty("location")
    val location: NamedApiResource<Location>?,
    /**
     * The minimum required level of the evolving Pokémon species to evolve into this Pokémon species.
     */
    @JsonProperty("min_level")
    val minLevel: Number,
    /**
     * The minimum required level of happiness the evolving Pokémon species to evolve into this Pokémon species.
     */
    @JsonProperty("min_happiness")
    val minHappiness: Number?,
    /**
     * The minimum required level of beauty the evolving Pokémon species to evolve into this Pokémon species.
     */
    @JsonProperty("min_beauty")
    val minBeauty: Number?,
    /**
     * The minimum required level of affection the evolving Pokémon species to evolve into this Pokémon species.
     */
    @JsonProperty("min_affection")
    val minAffection: Number?,
    /**
     * Whether or not it must be raining in the overworld to cause evolution this Pokémon species.
     */
    @JsonProperty("needs_overworld_rain")
    val needsOverworldRain: Boolean,
    /**
     * The Pokémon species that must be in the players party in order for the evolving Pokémon species to evolve into this Pokémon species.
     * @see NamedApiResource
     * @see PokemonSpecies
     */
    @JsonProperty("party_species")
    val partySpecies: Number?,
    /**
     * The player must have a Pokémon of this type in their party during the evolution trigger event in order for
     * the evolving Pokémon species to evolve into this Pokémon species.
     * @see NamedApiResource
     * @see Type
     */
    @JsonProperty("party_type")
    val partyType: NamedApiResource<Type>?,
    /**
     * The required relation between the Pokémon's Attack and Defense stats. 1 means Attack > Defense. 0 means Attack = Defense. -1 means Attack < Defense.
     */
    @JsonProperty("relative_physical_stats")
    val relativePhysicalStats: Number?,
    /**
     * The required time of day. Day or night.
     */
    @JsonProperty("time_of_day")
    val timeOfDay: String,
    /**
     * Pokémon species for which this one must be traded.
     * @see NamedApiResource
     * @see PokemonSpecies
     */
    @JsonProperty("trade_species")
    val tradeSpecies: NamedApiResource<PokemonSpecies>?,
    /**
     * Whether or not the 3DS needs to be turned upside-down as this Pokémon levels up.
     */
    @JsonProperty("turn_upside_down")
    val turnUpsideDown: Boolean
)
