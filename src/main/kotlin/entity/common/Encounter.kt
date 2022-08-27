package entity.common

/**
 * @see <a href="https://pokeapi.co/docs/v2#encounter">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class Encounter(

    /**
     * The lowest level the Pokémon could be encountered at.
     */
    val min_level: Number,

    /**
     * The highest level the Pokémon could be encountered at.
     */
    val max_level: Number,

    /**
     * A list of condition values that must be in effect for this encounter to occur.
     * @see EncounterConditionValue
     * @see NamedApiResource
     */
    val condition_values: Array<NamedApiResource>,

    /**
     * Percent chance that this encounter will occur.
     */
    val chance: Number,

    /**
     * The method by which this encounter happens.
     * @see EncounterMethod
     * @see NamedApiResource
     */
    val method: NamedApiResource

)