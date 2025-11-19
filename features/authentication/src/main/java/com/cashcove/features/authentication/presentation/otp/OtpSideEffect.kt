package com.cashcove.features.authentication.presentation.otp

sealed interface OtpSideEffect{
    data object NavigateToMain: OtpSideEffect
}