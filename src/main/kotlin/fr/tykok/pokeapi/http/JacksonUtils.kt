package fr.tykok.pokeapi.http

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class JacksonUtils {
    companion object {
        val mapper: ObjectMapper =
            jacksonObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)

        fun executeHttpRequest(url: String): Response {
            println(url)
            return Request
                .Builder()
                .url(url)
                .build()
                .let { request ->
                    OkHttpClient().newCall(request).execute()
                }
        }
    }
}
