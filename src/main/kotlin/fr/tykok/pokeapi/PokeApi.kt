package fr.tykok.pokeapi

import com.fasterxml.jackson.core.type.TypeReference
import fr.tykok.pokeapi.entities.PokeApiEndpointReference
import fr.tykok.pokeapi.entities.common.NamedApiResources
import fr.tykok.pokeapi.http.EndpointResolver
import fr.tykok.pokeapi.http.JacksonUtils

/**
 * PokeApi main class, used to fetch resources from the PokeApi RESTful API.
 * @author Tykok
 * @version 2.0
 * @see <a href="https://pokeapi.co/">PokeApi</a>
 * @see <a href="https://pokeapi.co/docs/v2">PokeApi Docs</a>
 */
abstract class PokeApi {
    companion object {
        private const val VERSION = "v2"
        const val BASE_URL = "https://pokeapi.co/api/$VERSION"

        /**
         * Get a resource by its id.
         */
        inline fun <reified T : PokeApiEndpointReference> get(id: Int): T =
            JacksonUtils
                .executeHttpRequest(url = "$BASE_URL/${EndpointResolver.resolve(T::class.java)}/$id")
                .let { response ->
                    JacksonUtils.mapper.readValue(response.body?.string(), T::class.java)
                }

        /**
         * Get a resource by its name.
         */
        inline fun <reified T : PokeApiEndpointReference> get(name: String): T =
            JacksonUtils
                .executeHttpRequest(url = "$BASE_URL/${EndpointResolver.resolve(T::class.java)}/$name")
                .let { response ->
                    JacksonUtils.mapper.readValue(response.body?.string(), T::class.java)
                }

        /**
         * Get a list of resources.
         */
        inline fun <reified T : PokeApiEndpointReference> get(
            limit: Int = 20,
            offset: Int = 20
        ): NamedApiResources<T> =
            JacksonUtils
                .executeHttpRequest(
                    url = "$BASE_URL/${EndpointResolver.resolve(T::class.java)}?offset=$offset&limit=$limit"
                ).let { response ->
                    JacksonUtils.mapper.readValue(
                        response.body?.string(),
                        object : TypeReference<NamedApiResources<T>>() {}
                    )
                }
    }
}
