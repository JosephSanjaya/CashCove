package com.cashcove.features.authentication.screens.otp.data.model

import com.cashcove.features.authentication.data.model.response.TokenModel
import com.cashcove.features.authentication.data.model.response.UserModel
import kotlinx.serialization.Serializable

@Serializable
data class VerifyOtpResponseDTO(
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
