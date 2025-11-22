package com.cashcove.features.authentication.data.entity.response

import kotlinx.serialization.Serializable

@Serializable
data class TokenModel(
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Int
)

