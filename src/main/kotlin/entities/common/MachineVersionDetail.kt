package entities.common

import com.fasterxml.jackson.annotation.JsonProperty
import entities.games.VersionGroup
import entities.machines.Machine

/**
 * @see <a href="https://pokeapi.co/docs/v2#machineversiondetail">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class MachineVersionDetail(
    /**
     * The machine that teaches a move from an item.
     * @see NamedApiResource
     * @see Machine
     */
    @JsonProperty("machine")
    val machine: APIResource,
    /**
     * The version group of this specific machine.
     * @see NamedApiResource
     * @see VersionGroup
     */
    @JsonProperty("version_group")
    val versionGroup: NamedApiResource<VersionGroup>
)
