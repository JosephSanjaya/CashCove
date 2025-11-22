package com.cashcove.features.authentication.data.entity.response

import kotlinx.serialization.Serializable

@Serializable
data class GetUserResponseModel(
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
        val message: String
    )
}

