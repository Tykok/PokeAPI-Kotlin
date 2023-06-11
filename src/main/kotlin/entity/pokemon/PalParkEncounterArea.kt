package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource

/**
 * @property baseScore The base score given to the player when the referenced Pokémon is caught during a pal park run.
 * @property rate The base rate for encountering the referenced Pokémon in this pal park area.
 * @property area The pal park area where this encounter happens.
 */
class PalParkEncounterArea(
    @JsonProperty("base_score")
    val baseScore: Number,

    @JsonProperty("rate")
    val rate: Number,

    @JsonProperty("area")
    val area: NamedApiResource<PalParkEncounterArea>
) {
    override fun toString(): String {
        return "PalParkEncounterArea(baseScore=$baseScore, rate=$rate, area=$area)"
    }
}
