package com.cashcove.features.authentication.data.entity.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserRequestModel(
    @SerialName("full_name")
    val fullName: String
)

