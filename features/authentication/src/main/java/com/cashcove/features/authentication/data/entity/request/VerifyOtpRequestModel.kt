package com.cashcove.features.authentication.data.entity.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VerifyOtpRequestModel(
    @SerialName("phone_number")
    val phoneNumber: String,
    val otp: String
)

