package fr.tykok.pokeapi.entities.pokemon

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.common.VerboseEffect
import fr.tykok.pokeapi.entities.contests.SuperContestEffects
import fr.tykok.pokeapi.entities.games.Generation

@JsonIgnoreProperties(ignoreUnknown = true)
data class Ability(
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
     * Whether this ability originated in the main series of the video games.
     */
    @JsonProperty("is_main_series")
    val isMainSeries: Boolean,
    /**
     * The generation this ability originated in.
     * @see Generation
     * @see NamedApiResource
     */
    @JsonProperty("generation")
    val generation: NamedApiResource<Generation>,
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
     * @see SuperContestEffects
     */
    @JsonProperty("effect_changes")
    val effectChanges: Array<SuperContestEffects>,
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
) : PokeApiObject
