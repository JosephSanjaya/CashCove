package com.cashcove.features.authentication.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenRequestModel(
    @SerialName("refresh_token")
    val refreshToken: String
)
