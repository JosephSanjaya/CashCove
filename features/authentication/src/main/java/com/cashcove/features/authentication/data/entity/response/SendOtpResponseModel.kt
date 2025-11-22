package com.cashcove.features.authentication.data.entity.response

import kotlinx.serialization.Serializable

@Serializable
data class SendOtpResponseModel(
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
        val message: String,
        val details: List<ErrorDetail>? = null
    ) {
        @Serializable
        data class ErrorDetail(
            val field: String? = null,
            val message: String
        )
    }
}