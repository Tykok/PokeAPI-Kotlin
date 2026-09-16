<div style="text-align:center;">

![project_img.png](docs/mkdocs-markdown/img/project_img.png)

</div>
<br/><br/>
<div style="text-align:center;">

[![javadoc](https://javadoc.io/badge2/fr.tykok/pokeapi/javadoc.svg)](https://javadoc.io/doc/fr.tykok/pokeapi)
[![Maven Central](https://img.shields.io/maven-central/v/fr.tykok/pokeapi)](https://central.sonatype.com/artifact/fr.tykok/pokeapi)
[![PokeApi Doc](https://img.shields.io/badge/PokeApi_documentation-blue)](https://tykok.github.io/PokeAPI-Kotlin/)
![GitHub](https://img.shields.io/github/license/Tykok/PokeAPI-Kotlin)
[![Discord](https://img.shields.io/discord/903774510648533012)](https://discord.gg/gryDvNE9)

</div>


This project is a library made with Kotlin you can use to fetch with simplicity the pokeapi.co API.
You just need to use the `PokeApi` class to make request here is an example :

```kotlin
val pikachu = PokeApi.get<Pokemon>("pikachu")
```

## Installation

To install this library, follow the instructions here.

### Kotlin: `build.gradle.kts`

```kotlin
dependencies {
    implementation("fr.tykok:pokeapi:3.0.0")
}
```

### Groovy: `build.gradle`

```groovy
dependencies {
    implementation 'fr.tykok:pokeapi:3.0.0'
}
```

### Maven: `pom.xml`

```xml
<dependencies>
    <dependency>
        <groupId>fr.tykok</groupId>
        <artifactId>pokeapi</artifactId>
        <version>3.0.0</version>
    </dependency>
</dependencies>
```

## Usage

```kotlin
// inside a coroutine
val pikachu = PokeApi.get<Pokemon>("pikachu")
val byId = PokeApi.get<Pokemon>(25)
val firstPage = PokeApi.list<Pokemon>(limit = 50, offset = 0)

// from synchronous code
val pikachu = PokeApi.getBlocking<Pokemon>("pikachu")
```

Every `get`/`list` call has a `getBlocking`/`listBlocking` counterpart for callers that are not
inside a coroutine — Java callers included.

### Errors

Every failure is a `PokeApiException`. Which one tells you what to do about it — not the HTTP status
code alone:

| Exception | What happened | What to do |
|---|---|---|
| `ResourceNotFoundException` | the name or id does not exist | recover — map to `null`, or fix the name |
| `PokeApiNetworkException`, or `PokeApiHttpException` with code `429` or `503` | transient infrastructure — a dropped connection, rate limiting, a momentary outage | retry with backoff |
| `PokeApiParseException`, `PokeApiHttpException` with any other non-2xx code, or `UnknownEndpointException` | the contract is broken — schema drift, a library bug, or a programming error (a type with no mapped endpoint) | stop and report it |

### Cache

Responses are cached on disk for 24 hours by default, because PokeApi's terms ask consumers to cache
locally rather than re-fetch static data.

```kotlin
PokeApi.get<Pokemon>("pikachu", refresh = true)   // bypass the cache for one call
PokeApi.cache.clear()                             // evict everything
PokeApi.cache.clear<Pokemon>()                     // evict one endpoint

val client = PokeApiClient(PokeApiConfig(cache = CacheConfig.Disabled))
```

**Concurrent instances need distinct cache directories.** The default `CacheConfig.OnDisk` directory
is one fixed path under the system temp directory. If you run more than one client at the same time —
two instances in one process, or two separate processes — give each its own `directory`:

```kotlin
val client = PokeApiClient(
    PokeApiConfig(cache = CacheConfig.OnDisk(directory = File("/var/myapp/pokeapi-cache-1")))
)
```

The library cannot pick a safe default for you here: it has no reliable way to tell your instances or
processes apart, and a directory unique to each run would defeat the very persistence a disk cache
exists for.

### Request logging

The library prints nothing. Supply your own client, with your own interceptor, to log requests:

```kotlin
val logging = Interceptor { chain ->
    val request = chain.request()
    println("${request.method} ${request.url}")
    chain.proceed(request)
}
val client = PokeApiClient(
    PokeApiConfig(httpClient = OkHttpClient.Builder().addInterceptor(logging).build())
)
```

### Coverage is not the same as verification

The entity classes are checked against real PokeAPI responses only for the endpoints exercised by
this repository's fixture tests — **10 of the library's 48 endpoints**. Every one of those fixtures
was added by pointing it at a real response, and doing so on this branch alone turned up **nine**
fields that PokeAPI actually returns as `null` while the class declared them non-null: `Move.power`,
`Move.accuracy`, `Move.contestCombos`, `Item.cost`, and four fields of `PastMoveStatValues`.

The other 38 endpoints have no fixture yet, so their nullability is unverified. If a call to one of
them raises a `PokeApiParseException` wrapping a `MismatchedInputException`-style cause, that is a bug
in this library worth reporting — not a mistake in how you called it.

## Migrating from 2.0.0

| 2.0.0 | 3.0.0 |
|---|---|
| `PokeApi.get<Pokemon>(25)` | same, but `suspend` — call from a coroutine, or use `getBlocking<Pokemon>(25)` |
| `PokeApi.get<Pokemon>(limit = 50, offset = 20)` | `PokeApi.list<Pokemon>(limit = 50, offset = 20)` — `suspend`, with `listBlocking` for synchronous callers |
| default `offset` is `20` | default `offset` is `0` |
| every numeric field is `Number` | every numeric field is `Int` |
| `Move.power`, `Move.accuracy`, `Move.contestCombos`, `Item.cost`, and four `PastMoveStatValues` fields are non-null | all nine are nullable, matching what PokeAPI actually returns |
| `PokeApiClient` has no lifecycle | `PokeApiClient` is `AutoCloseable` — `close()` it once you are done, if you built your own |
| no built-in caching | caching is on by default: on disk, 24h TTL |

## [Contributing Guide](./CONTRIBUTING.md)

Read our [contributing guide](./CONTRIBUTING.md) to learn about our development process, how to propose bugfixes and
improvements, and how to build and test your changes to our PokeAPi Kotlin library.

## Publish to Maven Central

Use a JDK 17 or 21 runtime for Gradle.

Local publish:

```bash
cp gradle-local.properties.example gradle-local.properties
./gradlew clean publishAndReleaseToMavenCentral
```

GitHub Actions secrets required:

- `MAVEN_CENTRAL_USERNAME`
- `MAVEN_CENTRAL_PASSWORD`
- `SIGNING_IN_MEMORY_KEY`
- `SIGNING_IN_MEMORY_KEY_PASSWORD`
