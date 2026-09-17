# Changelog
Changelog - PokeAPI extension 'changelog' property 'version'

## [Unreleased]

### Feat

### Clean

### Doc

### CI

### Fix

### Test

## [3.0.0] - 2026-09-17

A rewrite of the client surface: suspending calls with blocking equivalents, a configurable HTTP
cache, typed exceptions in place of raised generic errors, and eight fields corrected from non-null
to nullable after fixture tests were pointed at real PokeAPI responses.

**Known limitation:** the eight nullability fixes above were each found by adding a fixture for the
endpoint that returned the surprising `null`. The fixture suite now covers 10 of the library's 48
endpoints; the other 38 have not been checked against a real response and may hide the same kind of
mismatch. A `PokeApiParseException` on one of those is a bug report, not a usage error.

### Feat

- Map HTTP failures to typed exceptions (`ResourceNotFoundException`, `PokeApiHttpException`, `PokeApiNetworkException`, `PokeApiParseException`)
- Add a suspending API for `get`/`list`, alongside `getBlocking`/`listBlocking` for synchronous callers
- Rename the listing overload to `list`, so `get<Pokemon>(50)` can no longer be misread as "the Pokémon with id 50"
- Add a configurable HTTP cache, on by default, on disk, with a 24h TTL

### Clean

- Resolve endpoints with an annotation instead of a lookup table
- Type numeric fields as `Int` instead of `Number`
- Extract a configurable `PokeApiClient` out of the `PokeApi` singleton
- Keep the HTTP engine out of the published API
- Drop debug logging from the HTTP layer
- Untrack generated docs and IDE files

### Doc

- Add the 3.0.0 client rewrite design and implementation plan
- Rewrite the README and the documentation site for the 3.0.0 surface, including the cache's
  concurrent-directory caveat and the fixture coverage limitation above

### CI

- Move the Kotlin and ktlint versions to the version catalog

### Fix

- Share a single OkHttp client across requests instead of building one per call
- Let fatal errors escape the parse boundary instead of being wrapped
- Cancel the HTTP call for the whole suspending operation when its coroutine is cancelled
- Surface a fatal error instead of hanging the caller
- Reject a null read into a non-null numeric field instead of crashing
- Keep the default cache from disarming the test suite
- Accept the null `contest_combos` a healing move (e.g. `recover`) returns

### Test

- Cover the listing request path
- Cover the null accuracy a status move returns
- Make the endpoint cache assertion discriminating
- Stop the link-following tests inheriting cached state
- Complete entity deserialization coverage
