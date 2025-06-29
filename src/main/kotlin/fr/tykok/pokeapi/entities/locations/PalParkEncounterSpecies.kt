package fr.tykok.pokeapi.entities.locations
import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.pokemon.PokemonSpecies

/**
 * @see <a href="https://pokeapi.co/docs/v2#palparkencounterspecies">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
data class PalParkEncounterSpecies(
    /**
     * The base score given to the player when this Pokémon is caught during a pal park run
     */
    val baseScore: Number,
    /**
     * The base rate for encountering this Pokémon in this pal park area
     */
    val rate: Number,
    /**
     * The Pokémon species being encountered
     * @see NamedApiResource
     * @ee PokemonSpecies
     */
    val pokemonSpecies: NamedApiResource<PokemonSpecies>
) : PokeApiObject
