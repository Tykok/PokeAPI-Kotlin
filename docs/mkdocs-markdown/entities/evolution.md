# Evolution

## `EvolutionChain`

The `EvolutionChain` class can be used to make request to `/evolution-chain/{id}` endpoint.

```kotlin
val evolutionChain = PokeApi.get<EvolutionChain>(id = 1)
```

<span>You cannot used `PokeApi.get<EvolutionChain>(name = '...')` for this class</span>

## `EvolutionTrigger`

The `EvolutionChain` class can be used to make request to `/evolution-trigger/{id or name}` endpoint.

```kotlin
val evolutionTrigger = PokeApi.get<EvolutionTrigger>(id = 1)
```
