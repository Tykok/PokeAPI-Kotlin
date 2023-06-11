import entity.pokemon.Pokemon
import http.PokeApi

fun main(args: Array<String>) {
    val test = PokeApi.get<Pokemon>(limit = 100)
    println(test.results.size)
}
