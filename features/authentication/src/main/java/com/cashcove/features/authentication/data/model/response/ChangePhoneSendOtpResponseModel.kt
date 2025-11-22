package com.cashcove.features.authentication.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ChangePhoneSendOtpResponseModel(
    val success: Boolean,
    val data: OtpData? = null,
    val error: ErrorData? = null
) {
    @Serializable
    data class OtpData(
        val message: String,
        val expiresIn: Int
    )

    @Serializable
    data class ErrorData(
        val code: String,
        val message: String
    )
}

