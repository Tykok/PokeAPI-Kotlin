package fr.tykok.pokeapi.annotation

/**
 * Binds an entity to its PokeApi path segment.
 *
 * @property path the segment as it appears in the URL, for example `berry-firmness`
 *                in `https://pokeapi.co/api/v2/berry-firmness/1`.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Endpoint(
    val path: String
)
