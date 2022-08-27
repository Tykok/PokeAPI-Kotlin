package entity

/**
 * @link https://pokeapi.co/docs/v2#common-models
 * @author Tykok
 * @version 1.0.0
 * @since 2022-07-27
 */
class Name(

    /**
     * The localized name for an API resource in a specific language
     */
    val name: String,

    /**
     * The language this name is in.
     * @see NamedApiResource
     * @see Language
     */
    val language: NamedApiResource
)