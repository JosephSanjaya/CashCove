package com.cashcove.features.auth.otp.presentation

import com.cashcove.core.common.model.UiState

data class OtpState(
    val confirmOtpUiState: UiState<Unit> = UiState.Idle,
    val isTimerActive: Boolean = false,
    val timerValueString:String = "",
    val timerValue:Int = 60
)