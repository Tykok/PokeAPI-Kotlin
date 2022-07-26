package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.NamedApiResource

class TypeRelations(
    @JsonProperty("no_damage_to") val noDamageTo: Array<NamedApiResource>,
    @JsonProperty("half_damage_to") val halfDamageTo: Array<NamedApiResource>,
    @JsonProperty("double_damage_to") val doubleDamageTo: Array<NamedApiResource>,
    @JsonProperty("no_damage_from") val noDamageFrom: Array<NamedApiResource>,
    @JsonProperty("half_damage_from") val halfDamageFrom: Array<NamedApiResource>,
    @JsonProperty("double_damage_from") val doubleDamageFrom: Array<NamedApiResource>
)

