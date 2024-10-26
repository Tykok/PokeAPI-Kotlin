package fr.tykok.pokeapi.http

import fr.tykok.pokeapi.entities.berries.Berry
import fr.tykok.pokeapi.entities.berries.BerryFirmness
import fr.tykok.pokeapi.entities.berries.BerryFlavor
import fr.tykok.pokeapi.entities.contests.ContestEffect
import fr.tykok.pokeapi.entities.contests.ContestType
import fr.tykok.pokeapi.entities.contests.SuperContestEffects
import fr.tykok.pokeapi.entities.encounters.EncounterCondition
import fr.tykok.pokeapi.entities.encounters.EncounterConditionValue
import fr.tykok.pokeapi.entities.encounters.EncounterMethod
import fr.tykok.pokeapi.entities.evolution.EvolutionChain
import fr.tykok.pokeapi.entities.evolution.EvolutionTrigger
import fr.tykok.pokeapi.entities.games.Generation
import fr.tykok.pokeapi.entities.games.Pokedex
import fr.tykok.pokeapi.entities.games.Version
import fr.tykok.pokeapi.entities.games.VersionGroup
import fr.tykok.pokeapi.entities.items.Item
import fr.tykok.pokeapi.entities.items.ItemAttribute
import fr.tykok.pokeapi.entities.items.ItemCategory
import fr.tykok.pokeapi.entities.items.ItemFlingEffect
import fr.tykok.pokeapi.entities.items.ItemPocket
import fr.tykok.pokeapi.entities.locations.Location
import fr.tykok.pokeapi.entities.locations.LocationArea
import fr.tykok.pokeapi.entities.locations.PalParkArea
import fr.tykok.pokeapi.entities.locations.Region
import fr.tykok.pokeapi.entities.machines.Machine
import fr.tykok.pokeapi.entities.moves.Move
import fr.tykok.pokeapi.entities.moves.MoveAilment
import fr.tykok.pokeapi.entities.moves.MoveBattleStyle
import fr.tykok.pokeapi.entities.moves.MoveCategory
import fr.tykok.pokeapi.entities.moves.MoveDamageClass
import fr.tykok.pokeapi.entities.moves.MoveLearnMethod
import fr.tykok.pokeapi.entities.moves.MoveTarget
import fr.tykok.pokeapi.entities.pokemon.Ability
import fr.tykok.pokeapi.entities.pokemon.Characteristic
import fr.tykok.pokeapi.entities.pokemon.EggGroup
import fr.tykok.pokeapi.entities.pokemon.Gender
import fr.tykok.pokeapi.entities.pokemon.GrowthRate
import fr.tykok.pokeapi.entities.pokemon.Nature
import fr.tykok.pokeapi.entities.pokemon.PokeathlonStat
import fr.tykok.pokeapi.entities.pokemon.Pokemon
import fr.tykok.pokeapi.entities.pokemon.PokemonColor
import fr.tykok.pokeapi.entities.pokemon.PokemonForm
import fr.tykok.pokeapi.entities.pokemon.PokemonHabitat
import fr.tykok.pokeapi.entities.pokemon.PokemonLocationArea
import fr.tykok.pokeapi.entities.pokemon.PokemonShape
import fr.tykok.pokeapi.entities.pokemon.PokemonSpecies
import fr.tykok.pokeapi.entities.pokemon.Stat
import fr.tykok.pokeapi.entities.pokemon.Type
import fr.tykok.pokeapi.exception.UnknownEndpointException

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
