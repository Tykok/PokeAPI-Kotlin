package fr.tykok.pokeapi.entities.common

import fr.tykok.pokeapi.entities.PokeApiObject

data class NamedApiResources<T : PokeApiObject>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<NamedApiResource<T>>
) : PokeApiObject

/**
 * Fetches every referenced resource in this page.
 *
 * Entries whose [NamedApiResource.url] is `null` are silently omitted rather than fetched, so the
 * returned list can be shorter than [results]. Compare the returned list's size against
 * [NamedApiResources.results]'s size to detect that a page had fewer resolvable entries than it
 * reported.
 */
inline fun <reified T : PokeApiObject> NamedApiResources<T>.get(): List<T> = this.results.mapNotNull { it.get() }
