package fr.tykok.pokeapi.entities.games
import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource

/**
 * Versions of the games, e.g., Red, Blue or Yellow.
 *
 * @see <a href="https://bulbapedia.bulbagarden.net/wiki/Core_series">Bulbapedia documentation</a>
 * @see <a href="https://pokeapi.co/docs/v2#version">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class Version(
    /**
     * The identifier for this resource.
     */
    val id: Number,
    /**
     * The name for this resource.
     */
    val name: String,
    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    val names: List<Name>,
    /**
     * A list of version groups this Pokédex is relevant to.
     * @see NamedApiResource
     * @see VersionGroup
     */
    val versionGroup: NamedApiResource<VersionGroup>? = null
) : PokeApiEndpointReference
