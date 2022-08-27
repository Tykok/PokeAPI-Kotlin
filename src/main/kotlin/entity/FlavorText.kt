package entity


/**
 * @see <a href="https://pokeapi.co/docs/v2#common-models">Documentation of PokeApi</a>
 */
class FlavorText(

    /**
     * The localized flavor text for an API resource in a specific language
     */
    val flavor_text: String,

    /**
     * The language this name is in.
     * @see NamedApiResource
     */
    val language: NamedApiResource,

    /**
     * The game version this flavor text is extracted from.
     * @see NamedApiResource
     */
    val version: NamedApiResource
)