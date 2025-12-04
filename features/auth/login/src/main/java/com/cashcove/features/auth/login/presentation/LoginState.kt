package com.cashcove.features.auth.login.presentation

import com.cashcove.core.common.model.UiState
import com.cashcove.features.auth.login.domain.model.Login

data class LoginState(
    val loginUiState: UiState<Login> = UiState.Idle,
    val phoneNumber: String = "",
)
