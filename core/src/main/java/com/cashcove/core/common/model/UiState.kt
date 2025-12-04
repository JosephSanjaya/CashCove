package com.cashcove.core.common.model

/**
 * A generic UI State wrapper that can be used across the application.
 * Using sealed interface allows for exhaustive when branches.
 */
sealed interface UiState<out T> {
    data class Success<out T>(val data: T) : UiState<T>
    data class Error(val message: String? = null) : UiState<Nothing>
    data object Loading : UiState<Nothing>
    data object Idle : UiState<Nothing>
}
