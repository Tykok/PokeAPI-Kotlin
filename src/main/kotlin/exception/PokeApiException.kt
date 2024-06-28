package exception

import java.lang.Exception

abstract class PokeApiException(
    message: String
) : Exception(message)
