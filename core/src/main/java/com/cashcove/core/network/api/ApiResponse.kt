package com.cashcove.core.network.api

/**
 * Generic API response wrapper
 */
sealed class ApiResponse<out T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error(val error: com.cashcove.core.network.error.NetworkError) : ApiResponse<Nothing>()
    object Loading : ApiResponse<Nothing>()
}
