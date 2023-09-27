package exception

/**
 * Exception thrown when the endpoint is unknown.
 */
class UnknownEndpointException(className: String) : PokeApiException(
    "Unknown endpoint: ${PokeApi.BASE_URL}/$className"
)
