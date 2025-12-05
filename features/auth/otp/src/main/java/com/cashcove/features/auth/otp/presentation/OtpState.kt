package com.cashcove.features.auth.otp.presentation

import com.cashcove.core.common.model.UiState

data class OtpState(
    val confirmOtpUiState: UiState = UiState.Idle
)