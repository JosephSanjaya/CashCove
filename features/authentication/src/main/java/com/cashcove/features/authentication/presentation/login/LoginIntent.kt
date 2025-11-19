package com.cashcove.features.authentication.presentation.login

sealed interface LoginIntent {
    data object NavigateToRegister : LoginIntent
    data object NavigateToMain : LoginIntent
    data class UpdatePhoneNumber(val phoneNumber: String) : LoginIntent
    data class SendOtp(val phoneNumber: String) : LoginIntent
}
