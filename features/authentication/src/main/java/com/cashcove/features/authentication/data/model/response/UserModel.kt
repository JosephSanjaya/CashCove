package com.cashcove.features.authentication.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val id: String,
    val phoneNumber: String,
    val fullName: String,
    val isPhoneVerified: Boolean,
    val createdAt: String,
    val updatedAt: String? = null
)
