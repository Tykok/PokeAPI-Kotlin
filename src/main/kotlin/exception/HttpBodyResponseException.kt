package exception

/**
 * Exception thrown when the response body cannot be parsed.
 */
class HttpBodyResponseException(T: Any) : PokeApiException(
    message = "Cannot parse response body for ${T::class.simpleName}"
)
