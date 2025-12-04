package com.cashcove.features.auth.login.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SendOtpRequestDTO(
    @SerialName("phone_number")
    val phoneNumber: String
)