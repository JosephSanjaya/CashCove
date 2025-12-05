package com.cashcove.features.auth.otp.presentation

sealed interface OtpSideEffect {
    data object NavigateToMain : OtpSideEffect
    data object NavigateBack : OtpSideEffect
    data object NavigateToRegister : OtpSideEffect
    data object NavigateToLogin : OtpSideEffect
}