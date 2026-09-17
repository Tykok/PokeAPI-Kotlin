# Methods

Everything goes through `PokeApi`, and the endpoint is derived from the **type** you ask for — you
never write a URL:

```kotlin
import fr.tykok.pokeapi.PokeApi
import fr.tykok.pokeapi.entities.pokemon.Pokemon
```

The type must be one of the entities in the `fr.tykok.pokeapi.entities` package; the full list is in
the [API Reference](entities/index.md). Asking for an unmapped class throws `UnknownEndpointException`.

`get` and `list` are `suspend` functions — call them from a coroutine. Each has a blocking
counterpart, `getBlocking`/`listBlocking`, with the same parameters, for callers with no coroutine in
scope (Java included).

## `get(id)` — one resource by id

```kotlin
val pikachu = PokeApi.get<Pokemon>(id = 25)
// or, blocking:
val pikachu = PokeApi.getBlocking<Pokemon>(id = 25)
```

Resolves to `GET /pokemon/25`.

## `get(name)` — one resource by name

```kotlin
val pikachu = PokeApi.get<Pokemon>(name = "pikachu")
```

Resolves to `GET /pokemon/pikachu`. An unknown name throws `ResourceNotFoundException`.

!!! note "Not every resource has a name"

    A few endpoints are id-only. `EvolutionChain` is the usual one: use
    `PokeApi.get<EvolutionChain>(id = 1)`, never the `name` overload.

## `list(limit, offset)` — a page of resources

```kotlin
val page = PokeApi.list<Pokemon>(limit = 10, offset = 0)
// or, blocking:
val page = PokeApi.listBlocking<Pokemon>(limit = 10, offset = 0)
```

Resolves to `GET /pokemon?offset=0&limit=10` and returns `NamedApiResources<Pokemon>` — a page of
references, not fully-loaded entities. Fetch a reference with `get(name)` or `get(id)` when you need
its details.

| Parameter | Default | Meaning |
| --- | --- | --- |
| `limit` | `20` | How many references to return |
| `offset` | `0` | How many to skip first |

## `refresh` — bypass the cache

Every `get`/`list` overload takes a trailing `refresh: Boolean = false`. Pass `refresh = true` to
force that one call past the cache and back to the network:

```kotlin
val freshPikachu = PokeApi.get<Pokemon>(name = "pikachu", refresh = true)
```

See [Configuration](configuration.md#cacheconfig) for how the cache itself is configured, including
why concurrently running clients need distinct cache directories.

## Errors

Every failure is a `PokeApiException` subtype:

| Exception | What happened | What to do |
| --- | --- | --- |
| `ResourceNotFoundException` | the name or id does not exist | recover — map to `null`, or fix the name |
| `PokeApiNetworkException`, or `PokeApiHttpException` with code `429`/`503` | transient infrastructure | retry with backoff |
| `PokeApiParseException`, `PokeApiHttpException` with any other non-2xx code, or `UnknownEndpointException` | the contract is broken — schema drift, a library bug, or a programming error | stop and report it |
