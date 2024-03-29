import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import entity.common.NamedApiResources
import exception.HttpBodyResponseException
import http.getEndpoint
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

/**
 * PokeApi is a Kotlin wrapper for the PokeApi RESTful API.
 * @author Tykok
 * @version 1.0
 * @see <a href="https://pokeapi.co/">PokeApi</a>
 * @see <a href="https://pokeapi.co/docs/v2">PokeApi Docs</a>
 */
abstract class PokeApi {
    companion object PokeApi {
        private const val VERSION = "v2"
        const val BASE_URL = "https://pokeapi.co/api/$VERSION"

        /**
         * Get a resource by its id.
         */
        inline fun <reified T : Any> get(id: Int): T = fetch(resource = id)

        /**
         * Get a resource by its name.
         */
        inline fun <reified T : Any> get(name: String): T = fetch(resource = name)

        /**
         * Get a list of resources.
         */
        inline fun <reified T : Any> get(limit: Int = 20, offset: Int = 20) = fetchAll<T>(
            limit,
            offset
        )

        inline fun <reified T : Any> fetch(resource: Any? = ""): T =
            parseResponse(
                OkHttpClient().newCall(
                    createRequest("$BASE_URL/${getEndpoint<T>()}/$resource")
                ).execute()
            )

        inline fun <reified T : Any> fetch(fullUrl: String): T =
            parseResponse(
                OkHttpClient().newCall(
                    createRequest(fullUrl)
                ).execute()
            )

        inline fun <reified T : Any> fetchAll(limit: Int, offset: Int): NamedApiResources<T> {
            return parseResponses(
                OkHttpClient().newCall(
                    createRequest("$BASE_URL/${getEndpoint<T>()}?offset=$offset&limit=$limit")
                ).execute()
            )
        }

        fun createRequest(url: String): Request = Request.Builder().url(url).build()

        inline fun <reified T : Any> parseResponse(response: Response): T {
            try {
                return Gson().fromJson(response.body?.string(), T::class.java)
            } catch (e: Exception) {
                throw HttpBodyResponseException(T::class)
            }
        }

        inline fun <reified T : Any> parseResponses(response: Response): NamedApiResources<T> {
            try {
                return Gson().fromJson(
                    response.body?.string(),
                    object : TypeToken<NamedApiResources<T>>() {}.type
                )
            } catch (e: Exception) {
                throw HttpBodyResponseException(T::class)
            }
        }
    }
}
