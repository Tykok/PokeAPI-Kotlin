package entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * A class representing the sprites of a Pokémon.
 *
 * @property frontDefault The default depiction of this Pokémon from the front in battle.
 * @property frontShiny The shiny depiction of this Pokémon from the front in battle.
 * @property frontFemale The female depiction of this Pokémon from the front in battle.
 * @property frontShinyFemale The shiny female depiction of this Pokémon from the front in battle.
 * @property backDefault The default depiction of this Pokémon from the back in battle.
 * @property backShiny The shiny depiction of this Pokémon from the back in battle.
 * @property backFemale The female depiction of this Pokémon from the back in battle.
 * @property backShinyFemale The shiny female depiction of this Pokémon from the back in battle.
 */
data class PokemonSprites(
    @JsonProperty("front_default")
    val frontDefault: String?,
    @JsonProperty("front_shiny")
    val frontShiny: String?,
    @JsonProperty("front_female")
    val frontFemale: String?,
    @JsonProperty("front_shiny_female")
    val frontShinyFemale: String?,
    @JsonProperty("back_default")
    val backDefault: String?,
    @JsonProperty("back_shiny")
    val backShiny: String?,
    @JsonProperty("back_female")
    val backFemale: String?,
    @JsonProperty("back_shiny_female")
    val backShinyFemale: String?,
    @JsonProperty("back_gray")
    val backGray: String?,
    @JsonProperty("front_gray")
    val frontGray: String?,
    @JsonProperty("back_transparent")
    val backTransparent: String?,
    @JsonProperty("front_transparent")
    val frontTransparent: String?,
    @JsonProperty("back_shiny_transparent")
    val backShinyTransparent: String?,
    @JsonProperty("front_shiny_transparent")
    val frontShinyTransparent: String?,
    @JsonProperty("animated")
    val animated: PokemonSprites?,
    @JsonProperty("versions")
    val versions: Map<String, Map<String, PokemonSprites>>?,
    @JsonProperty("other")
    val other: Map<String, PokemonSprites>?
)
