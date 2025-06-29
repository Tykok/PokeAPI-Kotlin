package fr.tykok.pokeapi

import fr.tykok.pokeapi.entities.berries.Berry
import io.mockk.every
import io.mockk.mockk
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Test

class BerryTest {

    @Test
    fun `When try to get a berry with an existing id, then should return a berry `() {
        // Given
        val id = 1
        val responseMock = mockk<Response>()
        every { OkHttpClient().newCall(any()).execute() } returns responseMock
        every { responseMock.body } returns ("{\n" +
            "  \"id\": 1,\n" +
            "  \"name\": \"cheri\",\n" +
            "  \"growth_time\": 3,\n" +
            "  \"max_harvest\": 5,\n" +
            "  \"natural_gift_power\": 60,\n" +
            "  \"size\": 20,\n" +
            "  \"smoothness\": 25,\n" +
            "  \"soil_dryness\": 15,\n" +
            "  \"firmness\": {\n" +
            "    \"name\": \"soft\",\n" +
            "    \"url\": \"https://pokeapi.co/api/v2/berry-firmness/2/\"\n" +
            "  },\n" +
            "  \"flavors\": [\n" +
            "    {\n" +
            "      \"potency\": 10,\n" +
            "      \"flavor\": {\n" +
            "        \"name\": \"spicy\",\n" +
            "        \"url\": \"https://pokeapi.co/api/v2/berry-flavor/1/\"\n" +
            "      }\n" +
            "    }\n" +
            "  ],\n" +
            "  \"item\": {\n" +
            "    \"name\": \"cheri-berry\",\n" +
            "    \"url\": \"https://pokeapi.co/api/v2/item/126/\"\n" +
            "  },\n" +
            "  \"natural_gift_type\": {\n" +
            "    \"name\": \"fire\",\n" +
            "    \"url\": \"https://pokeapi.co/api/v2/type/10/\"\n" +
            "  }\n" +
            "}"
            ).toResponseBody("application/json".toMediaType())

        // When
        val berry = PokeApi.get<Berry>(1)

        // Then
        assertInstanceOf(Berry::class.java, berry)
        assertEquals(id, berry.id)
    }
}
