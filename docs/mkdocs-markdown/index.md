---
template: pokedex-home.html
hide:
  - toc
---

## How it works

You never name an endpoint. You name a **type**, and `PokeApi` resolves the endpoint from it:

```kotlin
val pikachu   = PokeApi.get<Pokemon>(name = "pikachu")   // GET /pokemon/pikachu
val cheriBerry = PokeApi.get<Berry>(id = 1)              // GET /berry/1
val firstTen  = PokeApi.get<Pokemon>(limit = 10, offset = 0) // GET /pokemon?offset=0&limit=10
```

Every response is mapped into a Kotlin data class, so the compiler — not the JSON — tells you what a
resource contains.

## Where to go next

- [Getting started](getting-started.md) — add the dependency to Gradle or Maven
- [Methods](methods.md) — the three `get()` overloads and their parameters
- [API Reference](entities/index.md) — all 48 endpoints, grouped
- [Contributing](https://github.com/Tykok/PokeAPI-Kotlin/blob/main/CONTRIBUTING.md) — issues and pull requests welcome
