package com.cashcove.features.auth.register.presentation

sealed interface RegisterSideEffect {
    data object NavigateToLogin : RegisterSideEffect
    data object NavigateToMain : RegisterSideEffect
    data object NavigateToOtpVerification : RegisterSideEffect
}
