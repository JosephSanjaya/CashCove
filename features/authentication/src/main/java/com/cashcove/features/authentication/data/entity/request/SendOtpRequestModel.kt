package com.cashcove.features.authentication.data.entity.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SendOtpRequestModel(
    @SerialName("phone_number")
    val phoneNumber: String
)