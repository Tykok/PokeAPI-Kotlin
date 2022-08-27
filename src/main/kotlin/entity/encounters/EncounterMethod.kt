package entity.encounters

import entity.Name

/**
 * EncounterMethod define how you can encounter a Pokemon.
 * Methods by which the player might can encounter a Pokemon in the wild.
 *
 * For example, many Pokemon are encountered in the tall grass.
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Wild_Pok%C3%A9mon">Bulbapedia documentation</a>
 * @see <a href="https://pokeapi.co/docs/v2#encounter-methods">Documentation of PokeApi</a>
 */
class EncounterMethod(


    /**
     * The identifier for this resource.
     */
    val id: Number,

    /**
     * The name for this resource.
     */
    val name: String,

    /**
     * A good value for sorting.
     */
    val order: Number,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    val names: Array<Name>
    )