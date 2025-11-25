package com.cashcove.core.ui.components

import androidx.compose.runtime.Composable
import com.cashcove.core.common.model.UiState

@Composable
fun <T> CashCoveContentLoader(uiState: UiState<T>, onSuccess: @Composable (T) -> Unit) {
    when (uiState) {
        is UiState.Success<T> -> onSuccess(uiState.data)
        is UiState.Error -> CashCoveError(uiState.message)
        else -> CashCoveLoading()
    }
}
