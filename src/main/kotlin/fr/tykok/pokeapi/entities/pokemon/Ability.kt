package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.common.VerboseEffect
import fr.tykok.pokeapi.entities.contests.SuperContestEffects
import fr.tykok.pokeapi.entities.games.Generation

data class Ability(
    /**
     * The identifier for this resource.
     */
    val id: Number,
    /**
     * The name for this resource.
     */
    val name: String,
    /**
     * Whether this ability originated in the main series of the video games.
     */
    val isMainSeries: Boolean,
    /**
     * The generation this ability originated in.
     * @see Generation
     * @see NamedApiResource
     */
    val generation: NamedApiResource<Generation>,
    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    val names: List<Name>,
    /**
     * The effect of this ability listed in different languages.
     * @see VerboseEffect
     */
    val effectEntries: List<VerboseEffect>,
    /**
     * The list of previous effects this ability has had across version groups.
     * @see SuperContestEffects
     */
    val effectChanges: List<SuperContestEffects>,
    /**
     * The flavor text of this ability listed in different languages.
     * @see AbilityFlavorText
     */
    val flavorTextEntries: List<AbilityFlavorText>,
    /**
     * A list of Pokémon that could potentially have this ability.
     * @see AbilityPokemon
     */
    val pokemon: List<AbilityPokemon>
) : PokeApiEndpointReference
