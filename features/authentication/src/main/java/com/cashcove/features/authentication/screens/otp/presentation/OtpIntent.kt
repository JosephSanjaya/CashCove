package com.cashcove.features.authentication.screens.otp.presentation

sealed interface OtpIntent {
    data object NavigateToMain : OtpIntent
    data object NavigateBack : OtpIntent
    data object ResendOtp : OtpIntent
    data class OtpEnter(val otp: String) : OtpIntent
    data class UpdatePhoneNumber(val phoneNumber: String) : OtpIntent
    data class UpdateOtpCode(val otpCode: String) : OtpIntent
}
