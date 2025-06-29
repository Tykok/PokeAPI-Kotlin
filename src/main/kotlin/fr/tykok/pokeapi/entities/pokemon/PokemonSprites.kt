package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiObject

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
    val frontDefault: String?,
    val frontShiny: String?,
    val frontFemale: String?,
    val frontShinyFemale: String?,
    val backDefault: String?,
    val backShiny: String?,
    val backFemale: String?,
    val backShinyFemale: String?,
    val backGray: String?,
    val frontGray: String?,
    val backTransparent: String?,
    val frontTransparent: String?,
    val backShinyTransparent: String?,
    val frontShinyTransparent: String?,
    val animated: PokemonSprites?,
    val versions: Map<String, Map<String, PokemonSprites>>?,
    val other: Map<String, PokemonSprites>?
) : PokeApiObject
