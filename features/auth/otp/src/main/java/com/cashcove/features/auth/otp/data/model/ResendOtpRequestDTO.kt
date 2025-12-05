package com.cashcove.features.auth.otp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResendOtpRequestDTO(
    @SerialName("phone_number") val phoneNumber: String
)