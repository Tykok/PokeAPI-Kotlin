package fr.tykok.pokeapi.entities.pokemon

import fr.tykok.pokeapi.entities.PokeApiEndpointReference
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
    val id: Int,
    val name: String,
    val gameIndex: String,
    val isBattleOnly: Boolean,
    val affectingMoves: MoveStatAffectSets,
    val affectingNatures: NatureStatAffectSets,
    val characteristics: List<APIResource>,
    val moveDamageClass: NamedApiResource<MoveDamageClass>?,
    val names: List<Name>
) : PokeApiEndpointReference
