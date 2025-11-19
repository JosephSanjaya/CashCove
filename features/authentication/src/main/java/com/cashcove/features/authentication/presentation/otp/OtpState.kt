package com.cashcove.features.authentication.presentation.otp

import com.cashcove.core.common.model.UiState

data class OtpState(
    val confirmOtpUiState: UiState = UiState.Idle
)