package fr.tykok.pokeapi.entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.berries.BerryFlavor
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource

data class Nature(
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
     * The stat decreased by 10% in Pokémon with this nature.
     * @see Stat
     * @see NamedApiResource
     */
    @JsonProperty("decreased_stat")
    val decreasedStat: NamedApiResource<Stat>?,
    /**
     * The stat increased by 10% in Pokémon with this nature.
     * @see Stat
     * @see NamedApiResource
     */
    @JsonProperty("increased_stat")
    val increasedStat: NamedApiResource<Stat>?,
    /**
     * The flavor hated by Pokémon with this nature.
     * @see BerryFlavor
     * @see NamedApiResource
     */
    @JsonProperty("hates_flavor")
    val hatesFlavor: NamedApiResource<BerryFlavor>?,
    /**
     * The flavor liked by Pokémon with this nature.
     * @see BerryFlavor
     * @see NamedApiResource
     */
    @JsonProperty("likes_flavor")
    val likesFlavor: NamedApiResource<BerryFlavor>?,
    /**
     * A list of Pokéathlon stats this nature effects and how much it effects them.
     * @see NatureStatChange
     */
    @JsonProperty("pokeathlon_stat_changes")
    val pokeathlonStatChanges: List<NatureStatChange>,
    /**
     * A list of battle styles and how likely a Pokémon with this nature is to use them in the Battle Palace or Battle Tent.
     * @see MoveBattleStylePreference
     */
    @JsonProperty("move_battle_style_preferences")
    val moveBattleStylePreferences: List<MoveBattleStylePreference>,
    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    @JsonProperty("names")
    val names: List<Name>
) : PokeApiObject
