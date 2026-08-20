# Locations

<div class="dex-entry">
  <span class="dex-entry__icon" aria-hidden="true">🗺</span>
  <span>
    <span class="dex-entry__no">No. 008</span>
    <span class="dex-entry__name">Location</span>
  </span>
  <span class="dex-entry__count">4 endpoints</span>
</div>

## `Location`

The `Location` class can be used to make request on `/location/{id or name}` endpoint.

```kotlin
val location = PokeApi.get<Location>(id = 1)
```

## `LocationArea`

The `LocationArea` class can be used to make request on `/location-area{id or name}` endpoint.

```kotlin
val locationArea = PokeApi.get<LocationArea>(id = 1)
```

## `PalParkArea`

The `PalParkArea` class can be used to make request on `/pal-park-area{id or name}` endpoint.

```kotlin
val palParkArea = PokeApi.get<PalParkArea>(id = 1)
```

## `Region`

The `Region` class can be used to make request on `/region{id or name}` endpoint.

```kotlin
val region = PokeApi.get<Region>(id = 1)
```
