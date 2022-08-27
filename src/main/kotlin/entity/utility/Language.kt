package entity.utility

import entity.common.Name

/**
 * Languages for translations of API resource information.
 * @link https://pokeapi.co/docs/v2#languages
 * @author Tykok
 * @version 1.0.0
 * @since 2022-07-27
 */
class Language(

    /**
     * The identifier for this resource
     */
    val id: Number,

    /**
     * The name for this resource.
     */
    val name: String,


    /**
     * Whether the games are published in this language.
     */
    val official: Boolean,

    /**
     * The two-letter code of the country where this language is spoken. Note that it is not unique.
     */
    val iso639: String,

    /**
     * The two-letter code of the language. Note that it is not unique.
     */
    val iso3166: String,

    /**
     * The name of this resource listed in different languages.
     * @see Name
     */
    val names: Array<Name>

)