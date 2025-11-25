package com.cashcove.features.authentication.screens.onboarding.presentation

import androidx.compose.runtime.Immutable
import com.cashcove.core.common.model.UiState
import com.cashcove.features.authentication.screens.onboarding.domain.model.OnboardingItemList

@Immutable
data class OnboardingState(
    val onboardingDataUiState: UiState<OnboardingItemList> = UiState.Idle,
)
