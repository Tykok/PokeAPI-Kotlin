package http

import entity.berries.Berry
import entity.berries.BerryFirmness
import entity.berries.BerryFlavor
import entity.contests.ContestEffect
import entity.contests.ContestType
import entity.contests.SuperContestEffects
import entity.encounters.EncounterCondition
import entity.encounters.EncounterConditionValue
import entity.encounters.EncounterMethod
import entity.evolution.EvolutionChain
import entity.evolution.EvolutionTrigger
import entity.games.Generation
import entity.games.Pokedex
import entity.games.Version
import entity.games.VersionGroup
import entity.items.Item
import entity.items.ItemAttribute
import entity.items.ItemCategory
import entity.items.ItemFlingEffect
import entity.items.ItemPocket
import entity.locations.Location
import entity.locations.LocationArea
import entity.locations.PalParkArea
import entity.locations.Region
import entity.machines.Machine
import entity.moves.Move
import entity.moves.MoveAilment
import entity.moves.MoveBattleStyle
import entity.moves.MoveCategory
import entity.moves.MoveDamageClass
import entity.moves.MoveLearnMethod
import entity.moves.MoveTarget
import entity.pokemon.Ability
import entity.pokemon.Characteristic
import entity.pokemon.EggGroup
import entity.pokemon.Gender
import entity.pokemon.GrowthRate
import entity.pokemon.Nature
import entity.pokemon.PokeathlonStat
import entity.pokemon.Pokemon
import entity.pokemon.PokemonColor
import entity.pokemon.PokemonForm
import entity.pokemon.PokemonHabitat
import entity.pokemon.PokemonLocationArea
import entity.pokemon.PokemonShape
import entity.pokemon.PokemonSpecies
import entity.pokemon.Stat
import entity.pokemon.Type
import exception.UnknownEndpointException

/**
 * Get the endpoint for a given class.
 */
inline fun <reified T : Any> getEndpoint(): String {
    val elementType = when (T::class) {
        /**
         * Berries
         */
        Berry::class -> "berry"
        BerryFirmness::class -> "berry-firmness"
        BerryFlavor::class -> "berry-flavor"

        /**
         * Contests
         */
        ContestType::class -> "contest-type"
        ContestEffect::class -> "contest-effect"
        SuperContestEffects::class -> "super-contest-effect"

        /**
         * Encounter
         */
        EncounterMethod::class -> "encounter-method"
        EncounterCondition::class -> "encounter-condition"
        EncounterConditionValue::class -> "encounter-condition-value"

        /**
         * Evolution
         */
        EvolutionChain::class -> "evolution-chain"
        EvolutionTrigger::class -> "evolution-trigger"

        /**
         * Games
         */
        Generation::class -> "generation"
        Pokedex::class -> "pokedex"
        Version::class -> "version"
        VersionGroup::class -> "version-group"

        /**
         * Item
         */
        Item::class -> "item"
        ItemAttribute::class -> "item-attribute"
        ItemCategory::class -> "item-category"
        ItemFlingEffect::class -> "item-fling-effect"
        ItemPocket::class -> "item-pocket"

        /**
         *Locations
         */
        Location::class -> "location"
        LocationArea::class -> "location-area"
        PalParkArea::class -> "pal-park-area"
        Region::class -> "region"

        /**
         * Machine
         */
        Machine::class -> "machine"

        /**
         * Moves
         */
        Move::class -> "move"
        MoveAilment::class -> "move-ailment"
        MoveBattleStyle::class -> "move-battle-style"
        MoveCategory::class -> "move-category"
        MoveDamageClass::class -> "move-damage-class"
        MoveLearnMethod::class -> "move-learn-method"
        MoveTarget::class -> "move-target"

        /**
         * Pokemon
         */
        Pokemon::class -> "pokemon"
        Ability::class -> "ability"
        Characteristic::class -> "characteristic"
        EggGroup::class -> "egg-group"
        Gender::class -> "gender"
        GrowthRate::class -> "growth-rate"
        Nature::class -> "nature"
        PokeathlonStat::class -> "pokeathlon-stat"
        PokemonLocationArea::class -> "pokemon-location-area"
        PokemonColor::class -> "pokemon-color"
        PokemonForm::class -> "pokemon-form"
        PokemonHabitat::class -> "pokemon-habitat"
        PokemonShape::class -> "pokemon-shape"
        PokemonSpecies::class -> "pokemon-species"
        Stat::class -> "stat"
        Type::class -> "type"

        else -> throw UnknownEndpointException(className = T::class.simpleName!!)
    }

    return elementType
}
