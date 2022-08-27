package entity.locations

import entity.common.NamedApiResource
import entity.common.VersionEncounterDetail
import entity.pokemon.Pokemon

/**
 * @see <a href="https://pokeapi.co/docs/v2#pokemonencounter">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class PokemonEncounter(
    /**
     * The Pokémon being encountered.
     * @see NamedApiResource
     * @see Pokemon
     */
    val pokemon: NamedApiResource,

    /**
     * A list of versions and encounters with Pokémon that might happen in the referenced location area.
     * @see VersionEncounterDetail
     */
    val version_details: Array<VersionEncounterDetail>
)