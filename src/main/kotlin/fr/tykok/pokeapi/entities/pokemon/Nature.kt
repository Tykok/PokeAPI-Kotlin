package fr.tykok.pokeapi.entities.pokemon
import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.berries.BerryFlavor
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource

data class Nature(
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
    val decreasedStat: NamedApiResource<Stat>?,
    /**
     * The stat increased by 10% in Pokémon with this nature.
     * @see Stat
     * @see NamedApiResource
     */
    val increasedStat: NamedApiResource<Stat>?,
    /**
     * The flavor hated by Pokémon with this nature.
     * @see BerryFlavor
     * @see NamedApiResource
     */
    val hatesFlavor: NamedApiResource<BerryFlavor>?,
    /**
     * The flavor liked by Pokémon with this nature.
     * @see BerryFlavor
     * @see NamedApiResource
     */
    val likesFlavor: NamedApiResource<BerryFlavor>?,
    /**
     * A list of Pokéathlon stats this nature effects and how much it effects them.
     * @see NatureStatChange
     */
    val pokeathlonStatChanges: List<NatureStatChange>,
    /**
     * A list of battle styles and how likely a Pokémon with this nature is to use them in the Battle Palace or Battle Tent.
     * @see MoveBattleStylePreference
     */
    val moveBattleStylePreferences: List<MoveBattleStylePreference>,
    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    val names: List<Name>
) : PokeApiEndpointReference
