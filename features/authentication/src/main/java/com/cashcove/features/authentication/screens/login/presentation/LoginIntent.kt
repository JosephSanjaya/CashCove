package com.cashcove.features.authentication.screens.login.presentation

sealed interface LoginIntent {
    data object NavigateToRegister : LoginIntent
    data object NavigateToMain : LoginIntent
    data class ChangePhoneNumber(val phoneNumber: String) : LoginIntent
    data class SendOtp(val phoneNumber: String) : LoginIntent
}
