package exception

class HttpBodyResponseException(T: Any) : PokeApiException(
    message = "Cannot parse response body for ${T::class.simpleName}"
)
