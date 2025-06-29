package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.NamedApiResource

/**
 * @property baseScore The base score given to the player when the referenced Pokémon is caught during a pal park run.
 * @property rate The base rate for encountering the referenced Pokémon in this pal park area.
 * @property area The pal park area where this encounter happens.
 */
data class PalParkEncounterArea(
    val baseScore: Number,
    val rate: Number,
    val area: NamedApiResource<PalParkEncounterArea>
) : PokeApiObject
