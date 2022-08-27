package entity.locations

import entity.common.Name

/**
 * @see <a href="https://pokeapi.co/docs/v2#pal-park-areas">Documentation of PokeApi</a>
 *
 * @author Tykok
 * @version 1.0.0
 * @since 2022-08-27
 */
class PalParkArea(
    /**
     * The identifier for this resource.
     */
    id: Number,

    /**
     * The name for this resource.
     */
    name: String,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    names: Array<Name>,

    /**
     * A list of Pokémon encountered in thi pal park area along with details.
     * @see PalParkEncounterSpecies
     */
    pokemon_encounters: Array<PalParkEncounterSpecies>
)