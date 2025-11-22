package com.cashcove.features.authentication.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ChangePhoneVerifyOtpResponseModel(
    val success: Boolean,
    val data: ChangePhoneData? = null,
    val error: ErrorData? = null
) {
    @Serializable
    data class ChangePhoneData(
        val message: String,
        val user: UserModel
    )

    @Serializable
    data class ErrorData(
        val code: String,
        val message: String
    )
}

