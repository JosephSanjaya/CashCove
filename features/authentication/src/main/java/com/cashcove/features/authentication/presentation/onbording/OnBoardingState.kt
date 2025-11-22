package com.cashcove.features.authentication.presentation.onbording

import androidx.compose.runtime.Immutable
import com.cashcove.core.common.model.UiState
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class OnBoardingState(
    val onboardingDataUiState: UiState<OnboardingPagesData> = UiState.Idle,
) {
    @Immutable
    data class OnboardingPagesData(
        val onboardingPages: PersistentList<OnboardingPage> = persistentListOf()
    )

    @Immutable
    data class OnboardingPage(
        val imageUrl: String = "",
        val title: String = "",
        val description: String = ""
    )
}