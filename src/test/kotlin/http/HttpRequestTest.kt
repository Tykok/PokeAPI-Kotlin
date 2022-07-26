package http

import org.junit.jupiter.api.Test

class HttpRequestTest {

    @Test
    fun shouldMakeRequest() {
        val response = makeRequest("https://pokeapi.co/api/v2/type/1")
        assert(response.isNotEmpty())
    }
}