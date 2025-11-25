package com.cashcove.features.authentication.screens.register.presentation

sealed interface RegisterIntent {
    data object NavigateToLogin : RegisterIntent
    data object NavigateToMain : RegisterIntent
    data class ChangePhoneNumber(val phoneNumber: String) : RegisterIntent
    data class SendOtp(val phoneNumber: String) : RegisterIntent
}
