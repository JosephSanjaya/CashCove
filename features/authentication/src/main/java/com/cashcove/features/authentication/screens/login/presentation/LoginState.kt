package com.cashcove.features.authentication.screens.login.presentation

import com.cashcove.core.common.model.UiState
import com.cashcove.features.authentication.screens.login.domain.model.Login

data class LoginState(
    val phoneNumber: String = "",
    val loginUiState: UiState<Login> = UiState.Idle
)
