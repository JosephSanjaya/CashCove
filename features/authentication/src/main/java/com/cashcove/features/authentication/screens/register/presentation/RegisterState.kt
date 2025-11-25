package com.cashcove.features.authentication.screens.register.presentation

import com.cashcove.core.common.model.UiState
import com.cashcove.features.authentication.screens.register.domain.model.Register

data class RegisterState(
    val phoneNumber: String = "",
    val registerUiState: UiState<Register> = UiState.Idle
)
