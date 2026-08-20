# API Reference

Every entity class maps one endpoint of [pokeapi.co](https://pokeapi.co/docs/v2). Pass the class as the generic parameter of `PokeApi.get()` and the endpoint is resolved for you:

```kotlin
val pikachu = PokeApi.get<Pokemon>(name = "pikachu")
```

48 endpoints are wired across 11 groups.

<div class="dex-grid" markdown>

[<span class="dex-entry__no">No. 001</span><span class="dex-entry__name">🐾 Pokemon</span><span class="dex-entry__sub">16 endpoints</span>](pokemon.md)

[<span class="dex-entry__no">No. 002</span><span class="dex-entry__name">🍒 Berry</span><span class="dex-entry__sub">3 endpoints</span>](berry.md)

[<span class="dex-entry__no">No. 003</span><span class="dex-entry__name">🏆 Contest</span><span class="dex-entry__sub">3 endpoints</span>](contest.md)

[<span class="dex-entry__no">No. 004</span><span class="dex-entry__name">🌿 Encounter</span><span class="dex-entry__sub">3 endpoints</span>](encounter.md)

[<span class="dex-entry__no">No. 005</span><span class="dex-entry__name">🧬 Evolution</span><span class="dex-entry__sub">2 endpoints</span>](evolution.md)

[<span class="dex-entry__no">No. 006</span><span class="dex-entry__name">🎮 Game</span><span class="dex-entry__sub">4 endpoints</span>](game.md)

[<span class="dex-entry__no">No. 007</span><span class="dex-entry__name">🎒 Item</span><span class="dex-entry__sub">5 endpoints</span>](item.md)

[<span class="dex-entry__no">No. 008</span><span class="dex-entry__name">🗺 Location</span><span class="dex-entry__sub">4 endpoints</span>](location.md)

[<span class="dex-entry__no">No. 009</span><span class="dex-entry__name">💿 Machine</span><span class="dex-entry__sub">1 endpoint</span>](machine.md)

[<span class="dex-entry__no">No. 010</span><span class="dex-entry__name">⚡ Move</span><span class="dex-entry__sub">7 endpoints</span>](move.md)

[<span class="dex-entry__no">No. 011</span><span class="dex-entry__name">🌐 Utility</span><span class="dex-entry__sub">reference only</span>](utility.md)

</div>

Asking for a class that is not mapped throws `UnknownEndpointException`.
