package entity.moves

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.APIResource
import entity.common.MachineVersionDetail
import entity.common.Name
import entity.common.NamedApiResource
import entity.common.VerboseEffect
import entity.contests.ContestType
import entity.contests.SuperContestEffects
import entity.games.Generation
import entity.pokemon.Pokemon
import entity.pokemon.Type

/**
 * Moves are the skills of Pokémon in battle. In battle, a Pokémon uses one move each turn.
 * Some moves (including those learned by Hidden Machine) can be used outside of battle as well,
 * usually for the purpose of removing obstacles or exploring new areas.
 *
 * @see <a href="https://pokeapi.co/docs/v2#moves">Documentation of PokeApi</a>
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Move">Bulbapedia documentation</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class Move(
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
     * The percent value of how likely this move is to be successful.
     */
    @JsonProperty("accuracy")
    val accuracy: Number,

    /**
     * The percent value of how likely it is this moves effect will happen.
     */
    @JsonProperty("effect_chance")
    val effectChance: Number? = null,

    /**
     * Power points. The number of times this move can be used.
     */
    @JsonProperty("pp")
    val pp: Number,

    /**
     * A value between -8 and 8. Sets the order in which moves are executed during battle. See Bulbapedia for greater detail.
     */
    @JsonProperty("priority")
    val priority: Number,

    /**
     * The base power of this move with a value of 0 if it does not have a base power.
     */
    @JsonProperty("power")
    val power: Number,

    /**
     * A detail of normal and super contest combos that require this move.
     * @see ContestComboSets
     */
    @JsonProperty("contest_combos")
    val contestCombos: ContestComboSets,

    /**
     * The type of appeal this move gives a Pokémon when used in a contest.
     * @see ContestType
     * @see NamedApiResource
     */
    @JsonProperty("contest_type")
    val contestType: NamedApiResource<ContestType>,

    /**
     * The effect the move has when used in a contest.
     * @see APIResource
     * @see ContestEffect
     */
    @JsonProperty("contest_effect")
    val contestEffect: APIResource,

    /**
     * The type of damage the move inflicts on the target, e.g. physical.
     * @ee NamedApiResource
     * @see MoveDamageClass
     */
    @JsonProperty("damage_class")
    val damageClass: NamedApiResource<MoveDamageClass>,

    /**
     * The effect of this move listed in different languages.
     * @see VerboseEffect
     */
    @JsonProperty("effect_entries")
    val effectEntries: List<VerboseEffect>,

    /**
     * The list of previous effects this move has had across version groups of the games.
     * @see SuperContestEffects
     */
    @JsonProperty("effect_changes")
    val effectChanges: List<SuperContestEffects>,

    /**
     * List of Pokemon that can learn the move
     * @see NamedApiResource
     * @see Pokemon
     */
    @JsonProperty("learned_by_pokemon")
    val learnedByPokemon: List<NamedApiResource<Pokemon>>,

    /**
     * The flavor text of this move listed in different languages.
     * @see MoveFlavorText
     */
    @JsonProperty("flavor_text_entries")
    val flavorTextEntries: List<MoveFlavorText>,

    /**
     * The generation in which this move was introduced.
     * @see NamedApiResource
     * @see Generation
     */
    @JsonProperty("generation")
    val generation: NamedApiResource<Generation>,

    /**
     * A list of the machines that teach this move.
     * @see MachineVersionDetail
     */
    @JsonProperty("machines")
    val machines: List<MachineVersionDetail>,

    /**
     * Metadata about this move
     * @see MoveMetaData
     */
    @JsonProperty("meta")
    val meta: MoveMetaData,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    @JsonProperty("names")
    val names: List<Name>,

    /**
     * A list of move resource value changes across version groups of the game.
     * @see PastMoveStatValues
     */
    @JsonProperty("past_values")
    val pastValues: List<PastMoveStatValues>,

    /**
     * A list of stats this moves effects and how much it effects them.
     * @see MoveStatChange
     */
    @JsonProperty("stat_changes")
    val statChanges: List<MoveStatChange>,

    /**
     * The effect the move has when used in a super contest.
     * @see APIResource
     * @see SuperContestEffect
     */
    @JsonProperty("super_contest_effect")
    val superContestEffect: APIResource,

    /**
     * The type of target that will receive the effects of the attack.
     * @see NamedApiResource
     * @see MoveTarget
     */
    @JsonProperty("target")
    val target: NamedApiResource<MoveTarget>,

    /**
     * The elemental type of this move.
     * @see NamedApiResource
     * @see Type
     */
    @JsonProperty("type")
    val type: NamedApiResource<Type>
) {
    override fun toString(): String {
        return "Move(id=$id, name='$name', accuracy=$accuracy, effectChance=$effectChance, pp=$pp, priority=$priority, power=$power, contestCombos=$contestCombos, contestType=$contestType, contestEffect=$contestEffect, damageClass=$damageClass, effectEntries=$effectEntries, effectChanges=$effectChanges, learnedByPokemon=$learnedByPokemon, flavorTextEntries=$flavorTextEntries, generation=$generation, machines=$machines, meta=$meta, names=$names, pastValues=$pastValues, statChanges=$statChanges, superContestEffect=$superContestEffect, target=$target, type=$type)"
    }
}
