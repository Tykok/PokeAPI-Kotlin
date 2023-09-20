# Contests

## `ContestType`

The `ContestType` class can be used to get data from `/contest-type/{id or name}`.

```kotlin
val contestType = PokeApi.get<ContestType>(id = 1)
```

## `ContestEffect`

The `ContestEffect` class can be used to get data from `/contest-effect/{id}`.

```kotlin
val contestEffect = PokeApi.get<ContestEffect>(id = 1)
```

## `SuperContestEffect`

The `SuperContestEffect` class can be used to get data from `/super-contest-effect/{id}`.

```kotlin
val superContestEffect = PokeApi.get<SuperContestEffect>(id = 1)
```
