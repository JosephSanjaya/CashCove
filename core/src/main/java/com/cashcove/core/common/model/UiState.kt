package com.cashcove.core.common.model

open class UiState {
    object Success : UiState()
    data class Error(
        val error: String,
        private val unique: Long = System.currentTimeMillis()
    ) : UiState()

    object Loading : UiState()
    object Idle : UiState()
}
