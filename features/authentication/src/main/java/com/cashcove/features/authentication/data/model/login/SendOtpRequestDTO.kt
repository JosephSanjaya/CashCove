package com.cashcove.features.authentication.data.model.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SendOtpRequestDTO(
    @SerialName("phone_number")
    val phoneNumber: String
)