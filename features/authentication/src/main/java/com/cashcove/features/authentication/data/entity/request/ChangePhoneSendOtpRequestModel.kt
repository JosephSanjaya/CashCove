package com.cashcove.features.authentication.data.entity.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChangePhoneSendOtpRequestModel(
    @SerialName("new_phone_number")
    val newPhoneNumber: String
)

