# Getting started

## Install

The library is published on Maven Central as `fr.tykok:pokeapi`. Pick your build system:

=== "Gradle (Kotlin DSL)"

    ```kotlin title="build.gradle.kts"
    dependencies {
        implementation("fr.tykok:pokeapi:{{ version }}")
    }
    ```

=== "Gradle (Groovy)"

    ```groovy title="build.gradle"
    dependencies {
        implementation 'fr.tykok:pokeapi:{{ version }}'
    }
    ```

=== "Maven"

    ```xml title="pom.xml"
    <dependencies>
        <dependency>
            <groupId>fr.tykok</groupId>
            <artifactId>pokeapi</artifactId>
            <version>{{ version }}</version>
        </dependency>
    </dependencies>
    ```

!!! tip "Version"

    The snippets above are filled in at build time from the version this site was published for, so
    they always match the chip in the top-right corner. The
    [Maven Central listing](https://central.sonatype.com/artifact/fr.tykok/pokeapi) is the
    authoritative index of every published release.

## Requirements

| | |
| --- | --- |
| JVM | 17 or later |
| Kotlin | 2.1 or later |
| Transitive dependencies | OkHttp, Jackson (`jackson-module-kotlin`) |

## First request

`get` and `list` are `suspend` functions, so they need a coroutine — here, `suspend fun main()`:

```kotlin
import fr.tykok.pokeapi.PokeApi
import fr.tykok.pokeapi.entities.pokemon.Pokemon

suspend fun main() {
    val pikachu = PokeApi.get<Pokemon>(name = "pikachu")
    println(pikachu.name)   // pikachu
    println(pikachu.weight) // 60
}
```

From synchronous code — no coroutine in scope — use the blocking counterpart instead:

```kotlin
val pikachu = PokeApi.getBlocking<Pokemon>(name = "pikachu")
```

Head to [Methods](methods.md) for every `get`/`list` overload, or browse the
[API Reference](entities/index.md) for every entity you can ask for.
