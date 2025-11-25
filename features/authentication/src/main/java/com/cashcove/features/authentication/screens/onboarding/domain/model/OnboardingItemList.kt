package com.cashcove.features.authentication.screens.onboarding.domain.model

import com.cashcove.features.authentication.screens.onboarding.data.model.OnboardingResponseDTO

data class OnboardingItemList(val data: List<OnboardingItem>)

internal fun OnboardingResponseDTO.toDomain(): OnboardingItemList =
    OnboardingItemList(
        data = this.data.map {
            OnboardingItem(
                title = it.title,
                description = it.description,
                imageUrl = it.imageUrl
            )
        }
    )
