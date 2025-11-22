package com.cashcove.features.authentication.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class VerifyOtpResponseModel(
    val success: Boolean,
    val data: VerifyOtpData? = null,
    val error: ErrorData? = null
) {
    @Serializable
    data class VerifyOtpData(
        val user: UserModel,
        val tokens: TokenModel
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

