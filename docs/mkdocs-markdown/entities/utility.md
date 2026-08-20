# Utility

<div class="dex-entry">
  <span class="dex-entry__icon" aria-hidden="true">🌐</span>
  <span>
    <span class="dex-entry__no">No. 011</span>
    <span class="dex-entry__name">Utility</span>
  </span>
  <span class="dex-entry__count">reference only</span>
</div>

## `Language`

`Language` describes one of the languages pokeapi.co translates its resources into, and mirrors the [`/language`](https://pokeapi.co/docs/v2#languages) resource:

```kotlin
data class Language(
    val id: Number,
    val name: String,
    val official: Boolean,
    val iso639: String,
    val iso3166: String,
    val names: List<Name>
)
```

!!! warning "Not reachable through `PokeApi.get()` yet"

    `Language` is not registered in `EndpointReference`, so `PokeApi.get<Language>(id = 1)` throws `UnknownEndpointException`. You will meet the class as the `language` field of the `names`, `descriptions` and `flavor_text_entries` lists returned by the other endpoints:

    ```kotlin
    val berry = PokeApi.get<BerryFlavor>(id = 1)
    val english = berry.names.first { it.language.name == "en" }
    ```
