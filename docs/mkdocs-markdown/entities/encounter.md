# Encouters

## EncounterMethod

The `EncounterMethod` class can be used to retrieve information about encounter methods from the
`/encounter-methods/{id or name}` endpoints of the API.

```kotlin
val encounterMethod = PokeAPI.get<EncounterMethod>(id = 1)
```

## `EncounterCondition`

The `EncounterCondition` class can be used to retrieve information about encounter conditions from the
`/encounter-condition/{id or name}` endpoints of the API.

```kotlin
val encounterCondition = PokeAPI.get<EncounterCondition>(id = 1)
```

## `EncounterConditionValue`

The `EncounterConditionValue` class can be used to retrieve information about encounter condition values from the
`/encounter-condition-values/{id or name}` endpoints of the API.

```kotlin
val encounterConditionValue = PokeAPI.get<EncounterConditionValue>(id = 1)
```
