package http

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

fun makeRequest(url: String): String {
    val okHttpClient = OkHttpClient()
    val parsedResponse = parseResponse(okHttpClient.newCall(createRequest(url)).execute())
    return parsedResponse
}

fun createRequest(url: String): Request {
    return Request.Builder()
        .url(url)
        .build()
}

fun parseResponse(response: Response): String {
    return response.body?.string() ?: ""
}
