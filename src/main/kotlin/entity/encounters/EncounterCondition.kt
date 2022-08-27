package entity.encounters

import entity.common.Name
import entity.common.NamedApiResource


/**
 * Conditions which affect what Pokemon might appear in the wild.
 * For example, you can encounter a Pokemon only at a certain hour.
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Wild_Pok%C3%A9mon">Bulbapedia documentation</a>
 * @see <a href="https://pokeapi.co/docs/v2#encounter-methods">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class EncounterCondition (

    /**
     * The identifier for this resource.
     */
    val id: Number,

    /**
     * The name for this resource.
     */
    val name: String,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    val names: Array<Name>,

    /**
     * A list of possible values for this encounter condition.
     * @see NamedApiResource
     * @see EncounterConditionValue
     */
    val values: Array<NamedApiResource>
)