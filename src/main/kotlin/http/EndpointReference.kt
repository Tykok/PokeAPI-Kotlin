package http

import entities.berries.Berry
import entities.berries.BerryFirmness
import entities.berries.BerryFlavor
import entities.contests.ContestEffect
import entities.contests.ContestType
import entities.contests.SuperContestEffects
import entities.encounters.EncounterCondition
import entities.encounters.EncounterConditionValue
import entities.encounters.EncounterMethod
import entities.evolution.EvolutionChain
import entities.evolution.EvolutionTrigger
import entities.games.Generation
import entities.games.Pokedex
import entities.games.Version
import entities.games.VersionGroup
import entities.items.Item
import entities.items.ItemAttribute
import entities.items.ItemCategory
import entities.items.ItemFlingEffect
import entities.items.ItemPocket
import entities.locations.Location
import entities.locations.LocationArea
import entities.locations.PalParkArea
import entities.locations.Region
import entities.machines.Machine
import entities.moves.Move
import entities.moves.MoveAilment
import entities.moves.MoveBattleStyle
import entities.moves.MoveCategory
import entities.moves.MoveDamageClass
import entities.moves.MoveLearnMethod
import entities.moves.MoveTarget
import entities.pokemon.Ability
import entities.pokemon.Characteristic
import entities.pokemon.EggGroup
import entities.pokemon.Gender
import entities.pokemon.GrowthRate
import entities.pokemon.Nature
import entities.pokemon.PokeathlonStat
import entities.pokemon.Pokemon
import entities.pokemon.PokemonColor
import entities.pokemon.PokemonForm
import entities.pokemon.PokemonHabitat
import entities.pokemon.PokemonLocationArea
import entities.pokemon.PokemonShape
import entities.pokemon.PokemonSpecies
import entities.pokemon.Stat
import entities.pokemon.Type
import exception.UnknownEndpointException

inline fun <reified T : Any> getEndpoint(): String {
    val elementType =
        when (T::class) {
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
