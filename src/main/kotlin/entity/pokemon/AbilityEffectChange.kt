package entity.pokemon

import entity.common.Effect
import entity.common.NamedApiResource
import entity.games.Generation

class AbilityEffectChange(
    /**
     * The previous effect of this ability listed in different languages.
     * @see Effect
     */
    val effect_entries: Array<Effect>,

    /**
     * The version group in which the previous effect of this ability originated.
     * @see NamedApiResource
     * @see Generation
     */
    val version_group: NamedApiResource
)