package com.cashcove.features.authentication.screens.login.presentation

sealed interface LoginSideEffect {
    data object NavigateToRegister : LoginSideEffect
    data object NavigateToMain : LoginSideEffect
    data class NavigateToOtpVerification(val phoneNumber: String) : LoginSideEffect
}
