package fr.tykok.pokeapi.entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import fr.tykok.pokeapi.entities.PokeApiObject
import fr.tykok.pokeapi.entities.common.GenerationGameIndex
import fr.tykok.pokeapi.entities.common.Name
import fr.tykok.pokeapi.entities.common.NamedApiResource
import fr.tykok.pokeapi.entities.games.Generation
import fr.tykok.pokeapi.entities.moves.Move
import fr.tykok.pokeapi.entities.moves.MoveDamageClass

data class Type(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("damage_relations")
    val damageRelations: TypeRelations,
    @JsonProperty("past_damage_relations")
    val pastDamageRelations: List<TypeRelationsPast>,
    @JsonProperty("game_indices")
    val gameIndices: List<GenerationGameIndex>,
    @JsonProperty("generation")
    val generation: NamedApiResource<Generation>,
    @JsonProperty("move_damage_class")
    val moveDamageClass: NamedApiResource<MoveDamageClass>,
    @JsonProperty("names")
    val names: List<Name>,
    @JsonProperty("pokemon")
    val pokemon: List<TypePokemon>,
    @JsonProperty("moves")
    val moves: List<NamedApiResource<Move>>
) : PokeApiObject
