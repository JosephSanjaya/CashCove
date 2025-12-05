package com.cashcove.features.auth.otp.presentation

sealed interface OtpIntent {
    data object NavigateToMain : OtpIntent
    data object ResendOtp : OtpIntent
    data object ToRegister : OtpIntent
    data object ToLogin : OtpIntent
    data object ToBack : OtpIntent
    data class OtpEnter(val otp: String) : OtpIntent
}