package fr.tykok.pokeapi.entities.machines

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.games.VersionGroup
import fr.tykok.pokeapi.entities.items.Item
import fr.tykok.pokeapi.entities.moves.Move

/**
 * Machines are the representation of items that teach moves to Pokémon.
 * They vary from version to version, so it is not certain that one specific TM or HM corresponds to a single Machine.
 *
 * @see <a href="https://pokeapi.co/docs/v2#machine">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class Machine(
    /**
     * The identifier for this resource.
     */
    @JsonProperty("id")
    val id: Number,
    /**
     * The TM or HM item that corresponds to this machine.
     * @see NamedApiResource
     * @see Item
     */
    @JsonProperty("item")
    val item: NamedApiResource<Item>,
    /**
     * The move that is taught by this machine.
     * @see NamedApiResource
     * @see Move
     */
    @JsonProperty("move")
    val move: NamedApiResource<Move>,
    /**
     * The version group that this machine applies to.
     * @see NamedApiResource
     * @see VersionGroup
     */
    @JsonProperty("version_group")
    val versionGroup: NamedApiResource<VersionGroup>
) : PokeApiObject
