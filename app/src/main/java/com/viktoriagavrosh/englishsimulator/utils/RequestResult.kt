package com.viktoriagavrosh.englishsimulator.utils

/**
 * Data source query result
 */
sealed class RequestResult<out T : Any>(open val data: T? = null) {
    class Success<T : Any>(override val data: T) : RequestResult<T>(data)
    class Error<T : Any>(val error: Throwable? = null) : RequestResult<T>()
}

fun <I : Any, O : Any> RequestResult<I>.map(mapper: (I) -> O): RequestResult<O> {
    return when (this) {
        is RequestResult.Success -> RequestResult.Success(mapper(data))
        is RequestResult.Error -> RequestResult.Error()
    }
}
