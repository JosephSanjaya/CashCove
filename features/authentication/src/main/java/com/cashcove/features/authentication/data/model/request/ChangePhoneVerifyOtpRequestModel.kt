package com.cashcove.features.authentication.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChangePhoneVerifyOtpRequestModel(
    @SerialName("new_phone_number")
    val newPhoneNumber: String,
    val otp: String
)

