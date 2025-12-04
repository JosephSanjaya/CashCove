package com.cashcove.features.auth.login.presentation

sealed interface LoginSideEffect {
    data object NavigateToRegister : LoginSideEffect
    data object NavigateToMain : LoginSideEffect
    data object NavigateToOtpVerification : LoginSideEffect
}
