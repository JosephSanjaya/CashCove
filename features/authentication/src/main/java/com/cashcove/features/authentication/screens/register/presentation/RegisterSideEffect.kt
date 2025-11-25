package com.cashcove.features.authentication.screens.register.presentation

sealed interface RegisterSideEffect {
    data object NavigateToLogin : RegisterSideEffect
    data object NavigateToMain : RegisterSideEffect
    data class NavigateToOtpVerification(val phoneNumber: String) : RegisterSideEffect
}
