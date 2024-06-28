# Methods

To use the methods, you need to import `PokeApi` class. Next, you can use the `get` methods to fetch data from the
[pokeapi.co][pokeapi].

You need to give the entities type as a generic parameter to the `get()` methods. The available entities types are available
in the `fr.tykok.pokeapi.entities` package, all the documentation are given in the `API Resource` section of this website.

All entities used to call a specific endpoint and is used to map the response of the endpoint.
Each entities are described in the entities section.

Finally, you need to give an `id`, the `name`, or the `limit` of the list to get the entities or the list of entities
given in the generic parameter.
All parameters for the `get()` method are described below.

## With `id` of the pokemon

You can get a pokemon with the `id` of the pokemon. For example, you can get the pokemon with `id` 25 <img
src="https://cdn-icons-png.flaticon.com/512/188/188987.png"
width="3%"> as Pikachu.

```kotlin
val pikachu = PokeApi.get<Pokemon>(id = 25)
```

## With `name` of the pokemon

Same thing, but here you can use the name of the pokemon. With the `name` of the pokemon you can get it <img
src="https://cdn-icons-png.flaticon.com/512/188/188987.png"
width="3%">.

```kotlin
val pikachu = PokeApi.get<Pokemon>(name = "pikachu")
```

If you give a wrong name, you will throw an exception.

## List of pokemon

You can get a list of pokemon with the `limit` of the list. For example, you can get the first 10 pokemon.

```kotlin
val pokemonList = PokeApi.get<Pokemon>(limit = 10)
```

You can also get a list of pokemon with the `limit` and the `offset` of the list. For example, you can get a list of
pokemon from 10 to 20 with `limit` = 10 and `offset` = 10 (you start at the 10th pokemon and you get the 10 next).

```kotlin
val pokemonList = PokeApi.get<Pokemon>(limit = 10, offset = 10)
```

[pokeapi]: https://pokeapi.co/
