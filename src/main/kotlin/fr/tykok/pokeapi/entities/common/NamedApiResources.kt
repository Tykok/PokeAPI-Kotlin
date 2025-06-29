package fr.tykok.pokeapi.entities.common

import fr.tykok.pokeapi.entities.PokeApiObject

data class NamedApiResources<T : PokeApiObject>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<NamedApiResource<T>>
) : PokeApiObject

inline fun <reified T : PokeApiObject> NamedApiResources<T>.get(): List<T> = this.results.map { it.get()!! }
