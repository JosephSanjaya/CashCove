package com.cashcove.features.auth.login.presentation

import com.cashcove.core.common.model.UiState

data class LoginState(
    val loginUiState: UiState<Nothing> = UiState.Idle,
    val phoneNumber: String = "",
)
