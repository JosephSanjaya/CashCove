package com.cashcove.features.auth.register.presentation

import com.cashcove.core.common.model.UiState
import com.cashcove.features.auth.register.domain.model.Register

data class RegisterState(
    val registerUiState: UiState<Register> = UiState.Idle,
    val phoneNumber: String = "",
)
