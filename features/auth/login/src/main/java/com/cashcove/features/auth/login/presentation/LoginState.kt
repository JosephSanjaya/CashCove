package com.cashcove.features.auth.login.presentation

import com.cashcove.core.common.model.UiState

data class LoginState(
    val loginUiState: UiState = UiState.Idle,
    val phoneNumber: String = "",
)
