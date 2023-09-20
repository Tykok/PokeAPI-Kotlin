# Games

## `Generation`

The `Generation` class can be used to get data from `/generation/{id or name}` endpoint.

```kotlin
val generation = PokeApi.get<Generation>(id = 1)
```

## `Pokedex`

The `Pokedex` class can be used to get data from `/pokedex/{id or name}` endpoint.

```kotlin
val pokedex = PokeApi.get<Pokedex>(id = 1)
```

## `Version`

The `Version` class can be used to get data from `/version/{id or name}` endpoint.

```kotlin
val version = PokeApi.get<Version>(id = 1)
```

## `VersionGroup`

The `VersionGroup` class can be used to get data from `/version-group/{id or name}` endpoint.

```kotlin
val versionGroup = PokeApi.get<VersionGroup>(id = 1)
```
