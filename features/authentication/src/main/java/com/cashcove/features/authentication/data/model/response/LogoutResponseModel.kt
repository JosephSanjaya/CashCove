package com.cashcove.features.authentication.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class LogoutResponseModel(
    val success: Boolean,
    val data: LogoutData? = null,
    val error: ErrorData? = null
) {
    @Serializable
    data class LogoutData(
        val message: String
    )

    @Serializable
    data class ErrorData(
        val code: String,
        val message: String
    )
}

