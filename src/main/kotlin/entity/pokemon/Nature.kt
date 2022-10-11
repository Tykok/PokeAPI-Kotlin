package entity.pokemon

import entity.common.Name
import entity.common.NamedApiResource
import entity.berries.BerryFlavor

class Nature(

    /**
     * The identifier for this resource.
     */
    val id: Number,

    /**
     * The name for this resource.
     */
    val name: String,

    /**
     * The stat decreased by 10% in Pokémon with this nature.
     * @see Stat
     * @see NamedApiResource
     */
    val decreased_stat: NamedApiResource,

    /**
     * The stat increased by 10% in Pokémon with this nature.
     * @see Stat
     * @see NamedApiResource
     */
    val increased_stat: NamedApiResource,

    /**
     * The flavor hated by Pokémon with this nature.
     * @see BerryFlavor
     * @see NamedApiResource
     */
    val hates_flavor: NamedApiResource,

    /**
     * The flavor liked by Pokémon with this nature.
     * @see BerryFlavor
     * @see NamedApiResource
     */
    val likes_flavor: NamedApiResource,

    /**
     * A list of Pokéathlon stats this nature effects and how much it effects them.
     * @see NatureStatChange
     */
    val pokeathlon_stat_changes: Array<NatureStatChange>,

    /**
     * A list of battle styles and how likely a Pokémon with this nature is to use them in the Battle Palace or Battle Tent.
     * @see MoveBattleStylePreference
     */
    val move_battle_style_preferences: Array<MoveBattleStylePreference>,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    val names: Array<Name>

)