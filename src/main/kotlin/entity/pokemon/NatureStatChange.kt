package entity.pokemon

import entity.common.NamedApiResource

class NatureStatChange(
    /**
     * The amount of change.
     */
    val max_change: Number,

    /**
     * The stat being affected.
     * @see PokeathlonStat
     * @see NamedApiResource
     */
    val pokeathlon_stat: NamedApiResource
)