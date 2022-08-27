package entity.encounters

import entity.Name
import entity.NamedApiResource

/**
 * Conditions which affect what Pokemon might appear in the wild.
 * For example, you can encounter a Pokemon only at a certain hour.
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Wild_Pok%C3%A9mon">Bulbapedia documentation</a>
 * @see <a href="https://pokeapi.co/docs/v2#encounter-methods">Documentation of PokeApi</a>
 */
class EncounterConditionValue(
    /**
     * The identifier for this resource.
     */
    val id: Number,

    /**
     *  The name for this resource.
     */
    val name: String,

    /**
     * The condition this encounter condition value pertains to.
     * @see NamedApiResource
     * @see EncounterCondition
     */
    val condition: NamedApiResource,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    val names: Array<Name>
)