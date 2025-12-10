package com.cashcove.features.auth.register.presentation

import com.cashcove.features.auth.register.data.model.SendOtpResponseDTO

sealed interface RegisterIntent {
    data object NavigateToLogin : RegisterIntent
    data object NavigateToMain : RegisterIntent
    data class UpdatePhoneNumber(val phoneNumber: String) : RegisterIntent
    data class SendOtp(val phoneNumber: String) : RegisterIntent
    data class OtpSent(val data: SendOtpResponseDTO) : RegisterIntent
}
