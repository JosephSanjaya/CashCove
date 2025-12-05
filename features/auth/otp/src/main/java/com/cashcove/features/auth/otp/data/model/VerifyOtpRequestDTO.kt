package com.cashcove.features.auth.otp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VerifyOtpRequestDTO(
    @SerialName("phone_number")
    val phoneNumber: String,
    val otp: String
)