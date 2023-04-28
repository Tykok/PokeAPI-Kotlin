package http

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class HttpRequest {

    companion object {
        private val VERSION = "v2"
        private val BASE_URL = "https://pokeapi.co/api/$VERSION"
    }

    /**
     * Makes a request to the PokeAPI and returns the response as a String.
     * To use this function, just pass in the endpoint you want to hit.
     */
    internal fun makeRequest(endpoint: String): String =
        parseResponse(OkHttpClient().newCall(createRequest("$BASE_URL/$endpoint")).execute())

    private fun createRequest(url: String): Request = Request.Builder().url(url).build()

    private fun parseResponse(response: Response): String = response.body?.string() ?: ""
}
