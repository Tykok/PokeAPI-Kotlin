package entity.encounters

import com.fasterxml.jackson.annotation.JsonProperty
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
class EncounterCondition(

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
     * The name of this resource listed in different languages.
     * @see Name
     */
    @JsonProperty("names")
    val names: Array<Name>,

    /**
     * A list of possible values for this encounter condition.
     * @see NamedApiResource
     * @see EncounterConditionValue
     */
    @JsonProperty("values")
    val values: Array<NamedApiResource<EncounterCondition>>
) {
    override fun toString() =
        "EncounterCondition(id=$id, name='$name', names=${names.contentToString()}, " +
            "values=${values.contentToString()})"
}
