package com.cashcove.features.authentication.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserResponseModel(
    val success: Boolean,
    val data: UserData? = null,
    val error: ErrorData? = null
) {
    @Serializable
    data class UserData(
        val user: UserModel
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

