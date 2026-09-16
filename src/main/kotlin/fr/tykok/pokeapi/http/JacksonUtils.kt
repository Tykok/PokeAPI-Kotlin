package fr.tykok.pokeapi.http

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import fr.tykok.pokeapi.PokeApiConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import kotlin.time.toJavaDuration

class JacksonUtils {
    companion object {
        val mapper: ObjectMapper =
            jacksonObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)

        fun executeHttpRequest(
            url: String,
            config: PokeApiConfig
        ): Response {
            println(url)
            val request =
                Request
                    .Builder()
                    .url(url)
                    .header("User-Agent", config.userAgent)
                    .build()
            val client =
                (config.httpClient ?: OkHttpClient())
                    .newBuilder()
                    .callTimeout(config.callTimeout.toJavaDuration())
                    .build()
            return client.newCall(request).execute()
        }
    }
}
