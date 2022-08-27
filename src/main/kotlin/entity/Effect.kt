package entity

/**
 * @see <a href="https://pokeapi.co/docs/v2#common-models">Documentation of PokeApi</a>
 */
class Effect (
    /**
     * The localized effect text for an API resource in a specific language
     */
    val effect: String,

    /**
     * The language this effect is in.
     * @see NamedApiResource
     */
    val language: NamedApiResource,
)