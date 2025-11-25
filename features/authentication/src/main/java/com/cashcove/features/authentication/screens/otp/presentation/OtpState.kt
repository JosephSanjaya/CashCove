package com.cashcove.features.authentication.screens.otp.presentation

import com.cashcove.core.common.model.UiState
import com.cashcove.features.authentication.screens.otp.domain.model.Otp

data class OtpState(
    val phoneNumber: String = "",
    val otpCode: String = "",
    val confirmOtpUiState: UiState<Otp> = UiState.Idle
)
