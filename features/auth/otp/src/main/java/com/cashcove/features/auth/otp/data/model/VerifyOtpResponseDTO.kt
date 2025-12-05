package com.cashcove.features.auth.otp.data.model

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
    ){
        @Serializable
        data class UserModel(
            val id: String,
            val phoneNumber: String,
            val fullName: String,
            val isPhoneVerified: Boolean,
            val createdAt: String,
            val updatedAt: String? = null
        )
        @Serializable
        data class TokenModel(
            val accessToken: String,
            val refreshToken: String,
            val expiresIn: Int
        )
    }

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