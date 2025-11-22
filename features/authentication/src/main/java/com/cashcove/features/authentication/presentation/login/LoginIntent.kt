package com.cashcove.features.authentication.presentation.login

import com.cashcove.features.authentication.data.model.login.SendOtpResponseDTO

sealed interface LoginIntent {
    data object NavigateToRegister : LoginIntent
    data object NavigateToMain : LoginIntent
    data class UpdatePhoneNumber(val phoneNumber: String) : LoginIntent
    data class SendOtp(val phoneNumber: String) : LoginIntent
    data class OtpSent(val data: SendOtpResponseDTO) : LoginIntent
    data class OtpError(val message: String) : LoginIntent
}
