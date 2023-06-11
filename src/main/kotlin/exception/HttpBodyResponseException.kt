package exception

class HttpBodyResponseException : Exception {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}
