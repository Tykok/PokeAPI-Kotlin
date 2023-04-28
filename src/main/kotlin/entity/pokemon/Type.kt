package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.GenerationGameIndex
import entity.common.Name
import entity.common.NamedApiResource

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
    val generation: NamedApiResource,

    @JsonProperty("move_damage_class")
    val moveDamageClass: NamedApiResource,

    @JsonProperty("names")
    val names: List<Name>,

    @JsonProperty("pokemon")
    val pokemon: List<TypePokemon>,

    @JsonProperty("moves")
    val moves: List<NamedApiResource>
) {
    override fun toString(): String {
        return "Type(id=$id, name='$name', damageRelations=$damageRelations, pastDamageRelations=$pastDamageRelations, gameIndices=$gameIndices, " +
            "generation=$generation, moveDamageClass=$moveDamageClass, names=$names, pokemon=$pokemon, moves=$moves)"
    }
}
