package com.cashcove.features.auth.otp.domain.model

data class OtpResult(
    val phoneNumber: String,
    val userId: String,
    val accessToken: String,
    val refreshToken: String,
)
