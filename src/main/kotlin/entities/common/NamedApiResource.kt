package entities.common

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * NamedApiResource contains the name and the url to get the object from the API resource (pokeapi.co).
 * @link https://bulbapedia.bulbagarden.net/wiki/Berry
 * @author Tykok
 * @version 1.0.0
 * @since 2022-07-27
 */
data class NamedApiResource<T : Any>(
    /**
     * The name of the referenced resource.
     */
    @JsonProperty("name")
    val name: String,
    /**
     * The URL of the referenced resource.
     */
    @JsonProperty("url")
    val url: String? = null,
    val resource: T? = null
)

inline fun <reified T : Any> NamedApiResource<T>.get(): T? =
    if (this.url != null) {
        Request
            .Builder()
            .url(this.url)
            .build()
            .let {
                OkHttpClient().newCall(it).execute()
            }.let {
                Gson().fromJson(it.body?.string(), T::class.java)
            }
    } else {
        null
    }
