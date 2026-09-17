# Machine

<div class="dex-entry">
  <span class="dex-entry__icon" aria-hidden="true">💿</span>
  <span>
    <span class="dex-entry__no">No. 009</span>
    <span class="dex-entry__name">Machine</span>
  </span>
  <span class="dex-entry__count">1 endpoint</span>
</div>

!!! info "Suspending calls"

    Every `PokeApi.get()` snippet below is `suspend` — see [Suspending calls](index.md#suspending-calls) for what that means and the `getBlocking` alternative.

## `Machine`

The class `Machine` can be used to get data from the `/machine/{id or name}` endpoint.

```kotlin
val machine = PokeApi.get<Machine>(id = 1)
```
