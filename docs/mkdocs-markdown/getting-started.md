# Getting started

## Install

The library is published on Maven Central as `fr.tykok:pokeapi`. Pick your build system:

=== "Gradle (Kotlin DSL)"

    ```kotlin title="build.gradle.kts"
    dependencies {
        implementation("fr.tykok:pokeapi:2.0.0")
    }
    ```

=== "Gradle (Groovy)"

    ```groovy title="build.gradle"
    dependencies {
        implementation 'fr.tykok:pokeapi:2.0.0'
    }
    ```

=== "Maven"

    ```xml title="pom.xml"
    <dependencies>
        <dependency>
            <groupId>fr.tykok</groupId>
            <artifactId>pokeapi</artifactId>
            <version>2.0.0</version>
        </dependency>
    </dependencies>
    ```

!!! tip "Latest version"

    The snippets above pin `2.0.0`. The badge on the [home page](index.md) always shows the latest
    release, and so does the
    [Maven Central listing](https://central.sonatype.com/artifact/fr.tykok/pokeapi).

## Requirements

| | |
| --- | --- |
| JVM | 17 or later |
| Kotlin | 2.1 or later |
| Transitive dependencies | OkHttp, Jackson (`jackson-module-kotlin`) |

## First request

```kotlin
import fr.tykok.pokeapi.PokeApi
import fr.tykok.pokeapi.entities.pokemon.Pokemon

fun main() {
    val pikachu = PokeApi.get<Pokemon>(name = "pikachu")
    println(pikachu.name)   // pikachu
    println(pikachu.weight) // 60
}
```

Head to [Methods](methods.md) for the full set of `get()` overloads, or browse the
[API Reference](entities/index.md) for every entity you can ask for.
