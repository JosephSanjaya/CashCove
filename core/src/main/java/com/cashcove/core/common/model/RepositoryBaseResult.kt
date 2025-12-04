package com.cashcove.core.common.model

sealed class RepositoryBaseResult<out T> {
    data class Success<out T>(val data: T) : RepositoryBaseResult<T>()
    data class Error(val exception: Throwable) : RepositoryBaseResult<Nothing>()
    data object Loading : RepositoryBaseResult<Nothing>()
}