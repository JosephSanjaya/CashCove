package com.cashcove.features.authentication.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class TokenModel(
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Int
)
