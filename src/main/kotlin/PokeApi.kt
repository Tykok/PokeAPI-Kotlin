import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import entities.PokeApiObject
import entities.common.NamedApiResources
import http.getEndpoint
import okhttp3.OkHttpClient
import okhttp3.Request

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
        inline fun <reified T : PokeApiObject> get(id: Int): PokeApiObject =
            "$BASE_URL/${getEndpoint<T>()}/$id"
                .let {
                    Request.Builder().url(it).build()
                }.let {
                    OkHttpClient().newCall(it).execute()
                }.let {
                    Gson().fromJson(it.body?.string(), T::class.java)
                }

        /**
         * Get a resource by its name.
         */
        inline fun <reified T : PokeApiObject> get(name: String): PokeApiObject =
            "$BASE_URL/${getEndpoint<T>()}/$name"
                .let {
                    Request.Builder().url(it).build()
                }.let {
                    OkHttpClient().newCall(it).execute()
                }.let {
                    Gson().fromJson(it.body?.string(), T::class.java)
                }

        /**
         * Get a list of resources.
         */
        inline fun <reified T : PokeApiObject> get(
            limit: Int = 20,
            offset: Int = 20
        ): NamedApiResources<PokeApiObject> =
            "$BASE_URL/${getEndpoint<T>()}?offset=$offset&limit=$limit"
                .let {
                    Request.Builder().url(it).build()
                }.let {
                    OkHttpClient().newCall(it).execute()
                }.let {
                    Gson().fromJson(it.body?.string(), object : TypeToken<NamedApiResources<T>>() {}.type)
                }
    }
}
