package entity.encounters

import entity.common.Name
import entity.common.NamedApiResource

/**
 * Encounter condition values are the various states that an encounter condition can have, i.e., time of day can be either day or night.
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Wild_Pok%C3%A9mon">Bulbapedia documentation</a>
 * @see <a href="https://pokeapi.co/docs/v2#encounter-methods">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
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