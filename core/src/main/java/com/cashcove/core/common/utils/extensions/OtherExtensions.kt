package com.cashcove.core.common.utils.extensions

import com.cashcove.core.common.model.RepositoryBaseResult
import com.cashcove.core.common.model.UiState
import com.cashcove.core.common.model.UsecaseBaseResult

fun <T> RepositoryBaseResult<T>.toUiState(): UiState<T> = when (this) {
    is RepositoryBaseResult.Loading -> UiState.Loading
    is RepositoryBaseResult.Error -> UiState.Error(this.exception.message)
    is RepositoryBaseResult.Success -> UiState.Success(data = this.data)
}

fun <T> UsecaseBaseResult<T>.toUiState(): UiState<T> = when (this) {
    is UsecaseBaseResult.Idle -> UiState.Idle
    is UsecaseBaseResult.Loading -> UiState.Loading
    is UsecaseBaseResult.Error -> UiState.Error(this.exception?.message)
    is UsecaseBaseResult.Success -> UiState.Success(data = this.data)
}