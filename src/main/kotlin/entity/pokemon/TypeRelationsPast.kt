package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource

class TypeRelationsPast(

    @JsonProperty("no_damage_to")
    val generation: NamedApiResource,

    @JsonProperty("damage_relations")
    val damageRelations: TypeRelations
) {
    override fun toString(): String {
        return "TypeRelationsPast(generation=$generation, damageRelations=$damageRelations)"
    }
}
