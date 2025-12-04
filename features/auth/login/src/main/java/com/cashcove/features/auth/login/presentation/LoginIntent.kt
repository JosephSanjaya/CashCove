package com.cashcove.features.auth.login.presentation

import com.cashcove.features.auth.login.data.model.SendOtpResponseDTO

sealed interface LoginIntent {
    data object NavigateToRegister : LoginIntent
    data object NavigateToMain : LoginIntent
    data class UpdatePhoneNumber(val phoneNumber: String) : LoginIntent
    data class SendOtp(val phoneNumber: String) : LoginIntent
    data class OtpSent(val data: SendOtpResponseDTO) : LoginIntent
}
