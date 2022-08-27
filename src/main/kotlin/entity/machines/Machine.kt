package entity.machines

import entity.common.NamedApiResource
import entity.items.Item
import entity.moves.Move
import entity.games.VersionGroup

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
class Machine(

    /**
     * The identifier for this resource.
     */
    val id: Number,

    /**
     * The TM or HM item that corresponds to this machine.
     * @see NamedApiResource
     * @see Item
     */
    val item: NamedApiResource,

    /**
     * The move that is taught by this machine.
     * @see NamedApiResource
     * @see Move
     */
    val move: NamedApiResource,

    /**
     * The version group that this machine applies to.
     * @see NamedApiResource
     * @see VersionGroup
     */
    val version_group: NamedApiResource
)