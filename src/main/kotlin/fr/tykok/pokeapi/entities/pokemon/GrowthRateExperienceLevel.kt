package fr.tykok.pokeapi.entities.pokemon

data class GrowthRateExperienceLevel(
    /**
     * The level gained.
     */
    val level: Number,
    /**
     * The amount of experience required to reach the referenced level.
     */
    val experience: Number
)
