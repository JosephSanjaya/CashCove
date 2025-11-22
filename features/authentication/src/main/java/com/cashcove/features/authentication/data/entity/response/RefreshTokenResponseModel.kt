package com.cashcove.features.authentication.data.entity.response

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponseModel(
    val success: Boolean,
    val data: RefreshTokenData? = null,
    val error: ErrorData? = null
) {
    @Serializable
    data class RefreshTokenData(
        val tokens: TokenModel
    )

    @Serializable
    data class ErrorData(
        val code: String,
        val message: String
    )
}

