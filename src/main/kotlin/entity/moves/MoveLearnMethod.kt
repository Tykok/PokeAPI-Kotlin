package entity.moves

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.Description
import entity.common.Name
import entity.common.NamedApiResource

/**
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-30
 *
 */
class MoveLearnMethod(

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
    val versionGroups: List<NamedApiResource>

) {
    override fun toString(): String {
        return "MoveLearnMethod(id=$id, name='$name', descriptions=$descriptions, names=$names, versionGroups=$versionGroups)"
    }
}