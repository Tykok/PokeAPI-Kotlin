# Berry

<div class="dex-entry">
  <span class="dex-entry__icon" aria-hidden="true">🍒</span>
  <span>
    <span class="dex-entry__no">No. 002</span>
    <span class="dex-entry__name">Berry</span>
  </span>
  <span class="dex-entry__count">3 endpoints</span>
</div>

!!! info "Suspending calls"

    Every `PokeApi.get()` snippet below is `suspend` — see [Suspending calls](index.md#suspending-calls) for what that means and the `getBlocking` alternative.

The `Berry` entities is used to map data from `https://pokeapi.co/api/v2/berry/...`.

![Berry](../img/berry.jpeg)

## `Berry`

The `Berry` class can be used to get data from `/berry/{id or name}`.

```kotlin
val berry = PokeApi.get<Berry>(id = 1)
```

## `BerryFirmness`

The `BerryFirmness` class can be used to et data from `/berry-firmness/{id or name}`.

```kotlin
val berryFirmness = PokeApi.get<BerryFirmness>(id = 1)
```

## `BerryFlavor`

The `BerryFlavor` class can be used to get data from `/berry-flavor/{id or name}`.

```kotlin
val berryFlavor = PokeApi.get<BerryFlavor>(id = 1)
```
