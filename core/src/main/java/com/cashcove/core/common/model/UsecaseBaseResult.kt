package com.cashcove.core.common.model

sealed interface UsecaseBaseResult<out T> {
    data class Success<out T>(val data: T) : UsecaseBaseResult<T>
    data class Error(val exception: Throwable? = null) : UsecaseBaseResult<Nothing>
    data object Loading : UsecaseBaseResult<Nothing>
    data object Idle : UsecaseBaseResult<Nothing>
}