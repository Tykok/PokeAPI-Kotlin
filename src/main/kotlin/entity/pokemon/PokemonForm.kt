package entity.pokemon

import com.fasterxml.jackson.annotation.JsonProperty
import entity.common.Name
import entity.common.NamedApiResource

/**
 * A class representing the details of a Pokémon form.
 *
 * @property id The identifier for this resource.
 * @property name The name for this resource.
 * @property order The order in which forms should be sorted within all forms. Multiple forms may have equal order, in which case they should fall back on sorting by name.
 * @property formOrder The order in which forms should be sorted within a species' forms.
 * @property isDefault True for exactly one form used as the default for each Pokémon.
 * @property isBattleOnly Whether or not this form can only happen during battle.
 * @property isMega Whether or not this form requires mega evolution.
 * @property formName The name of this form.
 * @property pokemon The Pokémon that can take on this form.
 * @property types A list of details showing types this Pokémon form has.
 * @property sprites A set of sprites used to depict this Pokémon form in the game.
 * @property versionGroup The version group this Pokémon form was introduced in.
 * @property names The form specific full name of this Pokémon form, or empty if the form does not have a specific name.
 * @property formNames The form specific form name of this Pokémon form, or empty if the form does not have a specific name.
 */
class PokemonForm(
    @JsonProperty("id")
    val id: Int,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("order")
    val order: Int,

    @JsonProperty("form_order")
    val formOrder: Int,

    @JsonProperty("is_default")
    val isDefault: Boolean,

    @JsonProperty("is_battle_only")
    val isBattleOnly: Boolean,

    @JsonProperty("is_mega")
    val isMega: Boolean,

    @JsonProperty("form_name")
    val formName: String,

    @JsonProperty("pokemon")
    val pokemon: NamedApiResource,

    @JsonProperty("types")
    val types: List<PokemonFormType>,

    @JsonProperty("sprites")
    val sprites: PokemonFormSprites,

    @JsonProperty("version_group")
    val versionGroup: NamedApiResource,

    @JsonProperty("names")
    val names: List<Name>,

    @JsonProperty("form_names")
    val formNames: List<Name>

) {
    override fun toString(): String {
        return "PokemonForm(id=$id, name='$name', order=$order, formOrder=$formOrder, isDefault=$isDefault, isBattleOnly=$isBattleOnly, isMega=$isMega, " +
                "formName='$formName', pokemon=$pokemon, types=$types, sprites=$sprites, versionGroup=$versionGroup, names=$name, formNames=$formName"
    }
}
