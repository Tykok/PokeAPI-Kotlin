# Contests

<div class="dex-entry">
  <span class="dex-entry__icon" aria-hidden="true">🏆</span>
  <span>
    <span class="dex-entry__no">No. 003</span>
    <span class="dex-entry__name">Contest</span>
  </span>
  <span class="dex-entry__count">3 endpoints</span>
</div>

!!! info "Suspending calls"

    Every `PokeApi.get()` snippet below is `suspend` — see [Suspending calls](index.md#suspending-calls) for what that means and the `getBlocking` alternative.

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

## `SuperContestEffects`

The `SuperContestEffects` class can be used to get data from `/super-contest-effect/{id}`.

```kotlin
val superContestEffects = PokeApi.get<SuperContestEffects>(id = 1)
```
