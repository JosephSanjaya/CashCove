package com.cashcove.features.authentication.presentation.login

import com.cashcove.features.authentication.data.entity.response.SendOtpResponseModel

sealed interface LoginIntent {
    data object NavigateToRegister : LoginIntent
    data object NavigateToMain : LoginIntent
    data class UpdatePhoneNumber(val phoneNumber: String) : LoginIntent
    data class SendOtp(val phoneNumber: String) : LoginIntent
    data class OtpSent(val data: SendOtpResponseModel) : LoginIntent
    data class OtpError(val message: String) : LoginIntent
}
