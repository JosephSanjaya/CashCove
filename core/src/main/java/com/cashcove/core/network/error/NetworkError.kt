package com.cashcove.core.network.error

sealed class NetworkError : Exception() {
    data class HttpError(
        val code: Int,
        override val message: String? = null
    ) : NetworkError()

    data class NetworkException(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : NetworkError()

    data class UnknownError(
        override val message: String? = null,
        override val cause: Throwable? = null
    ) : NetworkError()

    object NoInternetConnection : NetworkError()
    object Timeout : NetworkError()
}
