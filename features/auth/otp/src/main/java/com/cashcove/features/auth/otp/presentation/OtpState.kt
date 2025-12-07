package com.cashcove.features.auth.otp.presentation

import com.cashcove.core.common.model.UiState
import com.cashcove.features.auth.otp.domain.model.OtpResult

data class OtpState(
    val verifyOtpUiState: UiState<OtpResult> = UiState.Idle,
    val resendOtpUiState: UiState<OtpResult>  = UiState.Idle,
    val isTimerActive: Boolean = false,
    val timerValueString: String = "",
    val timerValue: Int = 60,
)