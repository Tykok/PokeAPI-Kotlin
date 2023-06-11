package entity.moves

import com.fasterxml.jackson.annotation.JsonProperty
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
    @JsonProperty("ailment")
    val ailment: NamedApiResource<MoveAilment>,

    /**
     * The category of move this move falls under, e.g. damage or ailment.
     * @see MoveCategory
     * @see NamedApiResource
     */
    @JsonProperty("category")
    val category: NamedApiResource<MoveCategory>,

    /**
     * The minimum number of times this move hits. Null if it always only hits once.
     */
    @JsonProperty("min_hits")
    val minHits: Number? = null,

    /**
     * The maximum number of times this move hits. Null if it always only hits once.
     */
    @JsonProperty("max_hits")
    val maxHits: Number? = null,

    /**
     * The minimum number of turns this move continues to take effect. Null if it always only lasts one turn.
     */
    @JsonProperty("min_turns")
    val minTurns: Number? = null,

    /**
     * The maximum number of turns this move continues to take effect. Null if it always only lasts one turn.
     */
    @JsonProperty("max_turns")
    val maxTurns: Number? = null,

    /**
     * HP drain (if positive) or Recoil damage (if negative), in percent of damage done.
     */
    @JsonProperty("drain")
    val drain: Number,

    /**
     * The amount of hp gained by the attacking Pokemon, in percent of it's maximum HP.
     */
    @JsonProperty("healing")
    val healing: Number,

    /**
     * Critical hit rate bonus.
     */
    @JsonProperty("crit_rate")
    val critRate: Number,

    /**
     * The likelihood this attack will cause an ailment.
     */
    @JsonProperty("ailment_chance")
    val ailmentChance: Number,

    /**
     * The likelihood this attack will cause the target Pokémon to flinch.
     */
    @JsonProperty("flinch_chance")
    val flinchChance: Number,

    /**
     * The likelihood this attack will cause a stat change in the target Pokémon.
     */
    @JsonProperty("stat_chance")
    val statChance: Number? = null

) {
    override fun toString(): String {
        return "MoveMetaData(ailment=$ailment, category=$category, minHits=$minHits, maxHits=$maxHits, minTurns=$minTurns, maxTurns=$maxTurns, drain=$drain, healing=$healing, crit_rate=$critRate, ailmentChance=$ailmentChance, flinchChance=$flinchChance, statChance=$statChance)"
    }
}
