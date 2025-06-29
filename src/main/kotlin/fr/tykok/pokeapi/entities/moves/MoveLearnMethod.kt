package fr.tykok.pokeapi.entities.moves

import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.Description
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.games.VersionGroup

/**
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-30
 *
 */
data class MoveLearnMethod(
    /**
     * The identifier for this resource.
     */
    val id: Number,
    /**
     * The name for this resource.
     */
    val name: String,
    /**
     * The description of this resource listed in different languages.
     * @see Description
     */
    val descriptions: List<Description>,
    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    val names: List<Name>,
    /**
     * A list of version groups where moves can be learned through this method.
     * @see NamedApiResource
     * @see VersionGroup
     */
    val versionGroups: List<NamedApiResource<VersionGroup>>
) : PokeApiEndpointReference
