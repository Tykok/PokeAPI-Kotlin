package fr.tykok.pokeapi.entities.common

import com.fasterxml.jackson.core.type.TypeReference
import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.http.JacksonUtils

/**
 * NamedApiResource contains the name and the url to get the object from the API resource (pokeapi.co).
 * @link https://bulbapedia.bulbagarden.net/wiki/Berry
 * @author Tykok
 * @version 1.0.0
 * @since 2022-07-27
 */
data class NamedApiResource<T : PokeApiObject>(
    /**
     * The name of the referenced resource.
     */
    val name: String,
    /**
     * The URL of the referenced resource.
     */
    val url: String? = null,
    val resource: T? = null
) : PokeApiObject

inline fun <reified T : PokeApiObject> NamedApiResource<T>.get(): T? =
    if (this.url != null) {
        val response = JacksonUtils.executeHttpRequest(url = this.url)
        JacksonUtils.mapper.readValue(response.body?.string(), object : TypeReference<T>() {})
    } else {
        null
    }
