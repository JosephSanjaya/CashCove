package com.cashcove.features.authentication.presentation.otp

sealed interface OtpIntent{
    data object NavigateToMain: OtpIntent
    data object ResendOtp: OtpIntent
    data class OtpEnter(val otp:String): OtpIntent
}