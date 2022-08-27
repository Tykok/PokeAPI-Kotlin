package entity.moves

import entity.common.*

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
    id: Number,

    /**
     * The name for this resource.
     */
    name: String,

    /**
     * The percent value of how likely this move is to be successful.
     */
    accuracy: Number,

    /**
     * The percent value of how likely it is this moves effect will happen.
     */
    effect_chance: Number,

    /**
     * Power points. The number of times this move can be used.
     */
    pp: Number,

    /**
     * A value between -8 and 8. Sets the order in which moves are executed during battle. See Bulbapedia for greater detail.
     */
    priority: Number,

    /**
     * The base power of this move with a value of 0 if it does not have a base power.
     */
    power: Number,

    /**
     * A detail of normal and super contest combos that require this move.
     * @see ContestComboSets
     */
    contest_combos: ContestComboSets,

    /**
     * The type of appeal this move gives a Pokémon when used in a contest.
     * @see ContestType
     * @see NamedApiResource
     */
    contest_type: NamedApiResource,

    /**
     * The effect the move has when used in a contest.
     * @see APIResource
     * @see ContestEffect
     */
    contest_effect: APIResource,

    /**
     * The type of damage the move inflicts on the target, e.g. physical.
     * @ee NamedApiResource
     * @see MoveDamageClass
     */
    damage_class: NamedApiResource,

    /**
     * The effect of this move listed in different languages.
     * @see VerboseEffect
     */
    effect_entries: Array<VerboseEffect>,

    /**
     * The list of previous effects this move has had across version groups of the games.
     * @see AbilityEffectChange
     */
    effect_changes: Array<AbilityEffectChange>,

    /**
     * List of Pokemon that can learn the move
     * @see NamedApiResource
     * @see Pokemon
     */
    learned_by_pokemon: Array<NamedApiResource>,

    /**
     * The flavor text of this move listed in different languages.
     * @see MoveFlavorText
     */
    flavor_text_entries: Array<MoveFlavorText>,

    /**
     * The generation in which this move was introduced.
     * @see NamedApiResource
     * @see Generation
     */
    generation: NamedApiResource,

    /**
     * A list of the machines that teach this move.
     * @see MachineVersionDetail
     */
    machines: Array<MachineVersionDetail>,

    /**
     * Metadata about this move
     * @see MoveMetaData
     */
    meta: MoveMetaData,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    names: Array<Name>,

    /**
     * A list of move resource value changes across version groups of the game.
     * @see PastMoveStatValues
     */
    past_values: Array<PastMoveStatValues>,

    /**
     * A list of stats this moves effects and how much it effects them.
     * @see MoveStatChange
     */
    stat_changes: Array<MoveStatChange>,

    /**
     * The effect the move has when used in a super contest.
     * @see APIResource
     * @see SuperContestEffect
     */
    super_contest_effect: APIResource,

    /**
     * The type of target that will receive the effects of the attack.
     * @see NamedApiResource
     * @see MoveTarget
     */
    target: NamedApiResource,

    /**
     * The elemental type of this move.
     * @see NamedApiResource
     * @see Type
     */
    type: NamedApiResource
)