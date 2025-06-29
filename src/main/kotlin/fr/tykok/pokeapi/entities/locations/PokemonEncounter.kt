package fr.tykok.pokeapi.entities.locations

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.common.VersionEncounterDetail
import fr.tykok.pokeapi.entities.pokemon.Pokemon

/**
 * @see <a href="https://pokeapi.co/docs/v2#pokemonencounter">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class PokemonEncounter(
    /**
     * The Pokémon being encountered.
     * @see NamedApiResource
     * @see Pokemon
     */
    val pokemon: NamedApiResource<Pokemon>,
    /**
     * A list of versions and encounters with Pokémon that might happen in the referenced location area.
     * @see VersionEncounterDetail
     */
    val versionDetails: List<VersionEncounterDetail>
) : PokeApiObject
