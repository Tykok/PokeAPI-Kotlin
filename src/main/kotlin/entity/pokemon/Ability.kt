package entity.pokemon

import entity.common.Name
import entity.common.NamedApiResource
import entity.common.VerboseEffect
import entity.games.Generation

class Ability (

    /**
    * The identifier for this resource.
    */
    val id: Number,

    /**
    * The name for this resource.
    */
    val name: String,

    /**
    * Whether or not this ability originated in the main series of the video games.
    */
    val is_main_series: Boolean,

    /**
    * The generation this ability originated in.
     * @see Generation
     * @see NamedApiResource
    */
    val generation: NamedApiResource,

    /**
    * The name of this resource listed in different languages.
     * @see Name
    */
    val names: Array<Name>,

    /**
    * The effect of this ability listed in different languages.
     * @see VerboseEffect
    */
    val effect_entries: Array<VerboseEffect>,

    /**
    * The list of previous effects this ability has had across version groups.
     * @see AbilityEffectChange
    */
    val effect_changes: Array<AbilityEffectChange>,

    /**
    * The flavor text of this ability listed in different languages.
     * @see AbilityFlavorText
    */
    val flavor_text_entries: Array<AbilityFlavorText>,

    /**
    * A list of Pokémon that could potentially have this ability.
     * @see AbilityPokemon
    */
    val pokemon: Array<AbilityPokemon>
)