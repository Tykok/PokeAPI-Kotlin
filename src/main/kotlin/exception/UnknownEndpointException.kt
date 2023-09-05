package exception

class UnknownEndpointException(className: String) : PokeApiException(
    "Unknown endpoint: ${PokeApi.BASE_URL}/$className"
)
