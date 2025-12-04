package com.cashcove.auth.onboarding.presentation

import androidx.compose.runtime.Immutable
import com.cashcove.auth.onboarding.domail.model.OnboardingItem
import com.cashcove.core.common.model.UiState
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class OnBoardingState(
    val onboardingDataUiState: UiState<OnboardingPagesData> = UiState.Idle,
) {
    @Immutable
    data class OnboardingPagesData(
        val onboardingPages: PersistentList<OnboardingItem> = persistentListOf()
    )
}