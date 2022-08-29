package entity.moves

import entity.common.NamedApiResource

/**
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class MoveMetaData(

    /**
     * The status ailment this move inflicts on its target.
     * @see MoveAilment
     * @see NamedApiResource
     */
    val ailment: NamedApiResource,

    /**
     * The category of move this move falls under, e.g. damage or ailment.
     * @see MoveCategory
     * @see NamedApiResource
     */
    val category: NamedApiResource,

    /**
     * The minimum number of times this move hits. Null if it always only hits once.
     */
    val min_hits: Number,

    /**
     * The maximum number of times this move hits. Null if it always only hits once.
     */
    val max_hits: Number,

    /**
     * The minimum number of turns this move continues to take effect. Null if it always only lasts one turn.
     */
    val min_turns: Number,

    /**
     * The maximum number of turns this move continues to take effect. Null if it always only lasts one turn.
     */
    val max_turns: Number,

    /**
     * HP drain (if positive) or Recoil damage (if negative), in percent of damage done.
     */
    val drain: Number,

    /**
     * The amount of hp gained by the attacking Pokemon, in percent of it's maximum HP.
     */
    val healing: Number,

    /**
     * Critical hit rate bonus.
     */
    val crit_rate: Number,

    /**
     * The likelihood this attack will cause an ailment.
     */
    val ailment_chance: Number,

    /**
     * The likelihood this attack will cause the target Pokémon to flinch.
     */
    val flinch_chance: Number,

    /**
     * The likelihood this attack will cause a stat change in the target Pokémon.
     */
    val stat_chance: Number

)