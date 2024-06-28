package entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty

data class PokemonFormSprites(
    @JsonProperty("front_default")
    val frontDefault: String?,
    @JsonProperty("front_shiny")
    val frontShiny: String?,
    @JsonProperty("back_default")
    val backDefault: String?,
    @JsonProperty("back_shiny")
    val backShiny: String?,
    @JsonProperty("back_female")
    val backFemale: String?,
    @JsonProperty("back_shiny_female")
    val backShinyFemale: String?,
    @JsonProperty("front_female")
    val frontFemale: String?,
    @JsonProperty("front_shiny_female")
    val frontShinyFemale: String?
)
