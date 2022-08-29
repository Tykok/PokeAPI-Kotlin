package entity.moves

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
    val id: Number,

    /**
     * The name for this resource.
     */
    val name: String,

    /**
     * The description of this resource listed in different languages.
     * @see Description
     */
    val descriptions: Array<Description>,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    val names: Array<Name>,

    /**
     * A list of version groups where moves can be learned through this method.
     * @see NamedApiResource
     * @see VersionGroup
     */
    val version_groups: Array<NamedApiResource>

)