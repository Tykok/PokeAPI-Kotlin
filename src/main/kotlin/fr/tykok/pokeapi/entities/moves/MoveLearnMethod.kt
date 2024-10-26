package fr.tykok.pokeapi.entities.moves

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.PokeApiObject
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
    @JsonProperty("id")
    val id: Number,
    /**
     * The name for this resource.
     */
    @JsonProperty("name")
    val name: String,
    /**
     * The description of this resource listed in different languages.
     * @see Description
     */
    @JsonProperty("descriptions")
    val descriptions: List<Description>,
    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    @JsonProperty("names")
    val names: List<Name>,
    /**
     * A list of version groups where moves can be learned through this method.
     * @see NamedApiResource
     * @see VersionGroup
     */
    @JsonProperty("version_groups")
    val versionGroups: List<NamedApiResource<VersionGroup>>
) : PokeApiObject
