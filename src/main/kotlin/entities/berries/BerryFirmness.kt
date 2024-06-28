package entities.berries

import com.fasterxml.jackson.annotation.JsonProperty
import entities.PokeApiObject
import entities.common.Name
import entities.common.NamedApiResource

/**
 * @link https://pokeapi.co/docs/v2#berry-firmnesses
 * @author Tykok
 * @version 1.0.0
 * @since 2022-07-27
 */
data class BerryFirmness(
    @JsonProperty("id")
    val id: Number,
    @JsonProperty("name")
    val name: String,
    /**
     * A list of the berries with this firmness.
     * @see NamedApiResource
     * @see Berry
     */
    @JsonProperty("berries")
    val berries: Array<NamedApiResource<Berry>>,
    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    @JsonProperty("names")
    val names: Array<Name>
) : PokeApiObject
