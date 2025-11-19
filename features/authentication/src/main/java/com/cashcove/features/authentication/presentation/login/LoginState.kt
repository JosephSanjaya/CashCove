package com.cashcove.features.authentication.presentation.login

import com.cashcove.core.common.model.UiState

data class LoginState(
    val loginUiState: UiState = UiState.Idle,
    val phoneNumber: String = "",
)
