package com.cashcove.core.common.model

/**
 * Result type for repository operations.
 * Repositories handle all API/network errors via NetworkError.
 */
sealed class RepositoryBaseResult<out T> {
    data class Success<out T>(val data: T) : RepositoryBaseResult<T>()
    data class Error(val exception: Throwable) : RepositoryBaseResult<Nothing>()
}