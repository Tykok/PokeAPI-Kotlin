package http

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import entity.common.NamedApiResources
import exception.HttpBodyResponseException
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

abstract class PokeApi {
    companion object PokeApi {
        private val VERSION = "v2"
        val BASE_URL = "https://pokeapi.co/api/$VERSION"

        inline fun <reified T : Any> get(id: Int): T = fetch(resource = id)

        inline fun <reified T : Any> get(name: String): T = fetch(resource = name)

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
                throw HttpBodyResponseException("Error parsing response body", e)
            }
        }

        inline fun <reified T : Any> parseResponses(response: Response): NamedApiResources<T> {
            try {
                return Gson().fromJson(
                    response.body?.string(),
                    object : TypeToken<NamedApiResources<T>>() {}.type
                )
            } catch (e: Exception) {
                throw HttpBodyResponseException("Error parsing response body", e)
            }
        }
    }
}
