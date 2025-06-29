package fr.tykok.pokeapi.entities.moves

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource

/**
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class MoveMetaData(
    /**
     * The status ailment this move inflicts on its target.
     * @see MoveAilment
     * @see NamedApiResource
     */
    val ailment: NamedApiResource<MoveAilment>,
    /**
     * The category of move this move falls under, e.g. damage or ailment.
     * @see MoveCategory
     * @see NamedApiResource
     */
    val category: NamedApiResource<MoveCategory>,
    /**
     * The minimum number of times this move hits. Null if it always only hits once.
     */
    val minHits: Number? = null,
    /**
     * The maximum number of times this move hits. Null if it always only hits once.
     */
    val maxHits: Number? = null,
    /**
     * The minimum number of turns this move continues to take effect. Null if it always only lasts one turn.
     */
    val minTurns: Number? = null,
    /**
     * The maximum number of turns this move continues to take effect. Null if it always only lasts one turn.
     */
    val maxTurns: Number? = null,
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
    val critRate: Number,
    /**
     * The likelihood this attack will cause an ailment.
     */
    val ailmentChance: Number,
    /**
     * The likelihood this attack will cause the target Pokémon to flinch.
     */
    val flinchChance: Number,
    /**
     * The likelihood this attack will cause a stat change in the target Pokémon.
     */
    val statChance: Number? = null
) : PokeApiObject
