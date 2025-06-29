package fr.tykok.pokeapi.entities.common

import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.games.Version

data class VersionGameIndex(
    val gameIndex: Int,
    /**
     * The version relevent to this game index.
     * @see NamedApiResource
     * @see Version
     */
    val version: NamedApiResource<Version>
) : PokeApiObject
