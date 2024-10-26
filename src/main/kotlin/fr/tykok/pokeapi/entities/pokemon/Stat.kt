package fr.tykok.pokeapi.entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.APIResource
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.moves.MoveDamageClass

/**
 * A stat refers to an aspect of a Pokémon that is used to represent its strengths and weaknesses. Each Pokémon has
 * six stats: HP, Attack, Defense, Special Attack, Special Defense, and Speed.
 *
 * @property id The identifier for this stat.
 * @property name The name for this stat.
 * @property gameIndex ID the games use for this stat.
 * @property isBattleOnly Whether this stat only exists within a battle.
 * @property affectingMoves A detail of moves which affect this stat positively or negatively.
 * @property affectingNatures A detail of natures which affect this stat positively or negatively.
 * @property characteristics A list of characteristics that are set on a Pokémon when its highest base stat is this stat.
 * @property moveDamageClass The class of damage this stat is directly related to.
 * @property names The name of this resource listed in different languages.
 */
data class Stat(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("game_index")
    val gameIndex: String,
    @JsonProperty("is_battle_only")
    val isBattleOnly: Boolean,
    @JsonProperty("affecting_moves")
    val affectingMoves: MoveStatAffectSets,
    @JsonProperty("affecting_natures")
    val affectingNatures: NatureStatAffectSets,
    @JsonProperty("characteristics")
    val characteristics: List<APIResource>,
    @JsonProperty("move_damage_class")
    val moveDamageClass: NamedApiResource<MoveDamageClass>?,
    @JsonProperty("names")
    val names: List<Name>
) : PokeApiObject
