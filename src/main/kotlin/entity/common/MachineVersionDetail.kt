package entity.common

import entity.games.VersionGroup
import entity.machines.Machine

/**
 * @see <a href="https://pokeapi.co/docs/v2#machineversiondetail">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class MachineVersionDetail(
    /**
     * The machine that teaches a move from an item.
     * @see NamedApiResource
     * @see Machine
     */
    val machine: APIResource,

    /**
     * The version group of this specific machine.
     * @see NamedApiResource
     * @see VersionGroup
     */
    val version_group: NamedApiResource,
)