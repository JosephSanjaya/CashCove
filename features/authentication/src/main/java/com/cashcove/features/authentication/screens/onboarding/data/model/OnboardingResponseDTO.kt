package com.cashcove.features.authentication.screens.onboarding.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OnboardingResponseDTO(
    val data: List<OnboardingItemDTO>
)

@Serializable
data class OnboardingItemDTO(
    val title: String,
    val description: String,
    @SerialName("image_url")
    val imageUrl: String,
)
