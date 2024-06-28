package entities.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entities.common.NamedApiResource

data class TypeRelationsPast(
    /**
     * A list of details of how effective this type was toward others and vice versa in previous generations
     * @see NamedApiResource
     * @see Type
     */
    @JsonProperty("no_damage_to")
    val generation: NamedApiResource<Type>,
    @JsonProperty("damage_relations")
    val damageRelations: TypeRelations
)
