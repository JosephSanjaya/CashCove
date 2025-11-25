package com.cashcove.features.authentication.screens.otp.presentation

sealed interface OtpSideEffect {
    data object NavigateToMain : OtpSideEffect
    data object NavigateBack : OtpSideEffect
}
