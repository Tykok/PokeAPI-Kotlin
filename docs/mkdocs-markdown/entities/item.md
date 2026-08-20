# Items

<div class="dex-entry">
  <span class="dex-entry__icon" aria-hidden="true">🎒</span>
  <span>
    <span class="dex-entry__no">No. 007</span>
    <span class="dex-entry__name">Item</span>
  </span>
  <span class="dex-entry__count">5 endpoints</span>
</div>

## `Item`

The `Item` class can be used to get data from `/item/{id or name}` endpoint.

```kotlin
val item = PokeApi.get<Item>(id = 1)
```

## `ItemAttribute`

The `ItemAttribute` class can be used to get data from `/item-attribute/{id or name}` endpoint.

```kotlin
val itemAttribute = PokeApi.get<ItemAttribute>(id = 1)
```

## `ItemCategory`

The `ItemCategory` class can be used to get data from `/item-category/{id or name}` endpoint.

```kotlin
val itemCategory = PokeApi.get<ItemCategory>(id = 1)
```

## `ItemFlingEffect`

The `ItemFlingEffect` class can be used to get data from `/item-fling-effect/{id or name}` endpoint.

```kotlin
val itemFlingEffect = PokeApi.get<ItemFlingEffect>(id = 1)
```

## `ItemPocket`

The `ItemPocket` class can be used to get data from `/item-pocket/{id or name}` endpoint.

```kotlin
val itemPocket = PokeApi.get<ItemPocket>(id = 1)
```
