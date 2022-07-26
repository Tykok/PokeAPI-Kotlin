package entity.pokemon

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.Name
import entity.common.NamedApiResource
import entity.common.VerboseEffect
import entity.games.Generation

@JsonIgnoreProperties(ignoreUnknown = true)
class Ability(

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
     * Whether or not this ability originated in the main series of the video games.
     */
    @JsonProperty("is_main_series")
    val isMainSeries: Boolean,

    /**
     * The generation this ability originated in.
     * @see Generation
     * @see NamedApiResource
     */
    @JsonProperty("generation")
    val generation: NamedApiResource,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    @JsonProperty("names")
    val names: Array<Name>,

    /**
     * The effect of this ability listed in different languages.
     * @see VerboseEffect
     */
    @JsonProperty("effect_entries")
    val effectEntries: Array<VerboseEffect>,

    /**
     * The list of previous effects this ability has had across version groups.
     * @see AbilityEffectChange
     */
    @JsonProperty("effect_changes")
    val effectChanges: Array<AbilityEffectChange>,

    /**
     * The flavor text of this ability listed in different languages.
     * @see AbilityFlavorText
     */
    @JsonProperty("flavor_text_entries")
    val flavorTextEntries: Array<AbilityFlavorText>,

    /**
     * A list of Pokémon that could potentially have this ability.
     * @see AbilityPokemon
     */
    @JsonProperty("pokemon")
    val pokemon: Array<AbilityPokemon>
) {
    override fun toString(): String {
        return "Ability(id=$id, name='$name', isMainSeries=$isMainSeries, generation=$generation, names=${names.contentToString()}, " +
            "effectEntries=${effectEntries.contentToString()}, effectChanges=${effectChanges.contentToString()}, " +
            "flavorTextEntries=${flavorTextEntries.contentToString()}, pokemon=${pokemon.contentToString()})"
    }
}
