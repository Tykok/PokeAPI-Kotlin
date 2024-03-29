<div align="center">

![project_img.png](docs/mkdocs-markdown/img/project_img.png)

</div>
<br/><br/>
<div align="center">

[![javadoc](https://javadoc.io/badge2/fr.tykok/pokeapi/javadoc.svg)](https://javadoc.io/doc/fr.tykok/pokeapi)
[![Maven Central](https://img.shields.io/maven-central/v/fr.tykok/pokeapi)](https://central.sonatype.com/artifact/fr.tykok/pokeapi)
[![PokeApi Doc](https://img.shields.io/badge/PokeApi_documentation-blue)](https://tykok.github.io/PokeAPI-Kotlin/)
![GitHub](https://img.shields.io/github/license/Tykok/PokeAPI-Kotlin)
[![Discord](https://img.shields.io/discord/903774510648533012)](https://discord.gg/gryDvNE9)

</div>


This project is a library made with Kotlin you can use to fetch with simplicity the pokeapi.co API.
You just need to use the `PokeApi` class to make request here is an example :

```kotlin
val pikachu = PokeApi.get<Pokemon>("pickachu")
```

## Installation

To install this library, follow the instructions here.

### Kotlin: `build.gradle.kts`

```kotlin
dependencies {
    implementation("fr.tykok.pokeapi:{version}")
}
```

### Groovy: `build.gradle`

```groovy
dependencies {
    implementation 'fr.tykok.pokeapi:{version}'
}
```

### Maven: `pom.xml`

```xml

<dependencies>
    <dependecy>
        <groupId>fr.tykok.pokeapi</groupId>
        <artifactId>{version}</artifactId>
    </dependecy>
</dependencies>
```

## Usage

To use te library, you just need to use the `PokeApi` class to make request, and use one of the classes given by the
library to get the data of the endpoint you want.

Here is an example :

```kotlin
val pikachu = PokeApi.get<Pokemon>("pickachu")
```

### Methods

To make a request on an endpoint, you need to use the one of the method provided in the `PokeApi` class described below.

With the name of the Pokemon:

```kotlin
val pikachu = PokeApi.get<Pokemon>("pickachu")
```

Or, with the id of the Pokemon:

```kotlin
val pikachu = PokeApi.get<Pokemon>(25)
```

Or, with a limit an offset:

```kotlin
val pokemons = PokeApi.get<Pokemon>(limit = 10, offset = 10)
```

## [Contributing Guide](./CONTRIBUTING.md)

Read our [contributing guide](./CONTRIBUTING.md) to learn about our development process, how to propose bugfixes and
improvements, and how to build and test your changes to our PokeAPi Kotlin library.
