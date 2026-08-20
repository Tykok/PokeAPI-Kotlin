# Methods

Everything goes through `PokeApi`, and the endpoint is derived from the **type** you ask for — you
never write a URL:

```kotlin
import fr.tykok.pokeapi.PokeApi
import fr.tykok.pokeapi.entities.pokemon.Pokemon
```

The type must be one of the entities in the `fr.tykok.pokeapi.entities` package; the full list is in
the [API Reference](entities/index.md). Asking for an unmapped class throws `UnknownEndpointException`.

## `get(id)` — one resource by id

```kotlin
val pikachu = PokeApi.get<Pokemon>(id = 25)
```

Resolves to `GET /pokemon/25`.

## `get(name)` — one resource by name

```kotlin
val pikachu = PokeApi.get<Pokemon>(name = "pikachu")
```

Resolves to `GET /pokemon/pikachu`. An unknown name makes the call throw.

!!! note "Not every resource has a name"

    A few endpoints are id-only. `EvolutionChain` is the usual one: use
    `PokeApi.get<EvolutionChain>(id = 1)`, never the `name` overload.

## `get(limit, offset)` — a page of resources

```kotlin
val page = PokeApi.get<Pokemon>(limit = 10, offset = 0)
```

Resolves to `GET /pokemon?offset=0&limit=10` and returns `NamedApiResources<Pokemon>` — a page of
references, not fully-loaded entities. Fetch a reference with `get(name)` or `get(id)` when you need
its details.

| Parameter | Default | Meaning |
| --- | --- | --- |
| `limit` | `20` | How many references to return |
| `offset` | `20` | How many to skip first |

!!! warning "`offset` defaults to 20, not 0"

    `PokeApi.get<Pokemon>(limit = 10)` returns entries **21 to 30**, not the first ten. Pass
    `offset = 0` explicitly whenever you want to start at the beginning.
