package entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty

data class GrowthRateExperienceLevel(
    /**
     * The level gained.
     */
    @JsonProperty("level")
    val level: Number,
    /**
     * The amount of experience required to reach the referenced level.
     */
    @JsonProperty("experience")
    val experience: Number
)
