package com.cashcove.features.authentication.screens.otp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VerifyOtpRequestDTO(
    @SerialName("phone_number")
    val phoneNumber: String,
    val otp: String
)
