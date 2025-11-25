package com.cashcove.features.authentication.screens.onboarding.presentation

import androidx.lifecycle.viewModelScope
import com.cashcove.core.viewmodel.BaseViewModel
import com.cashcove.features.authentication.screens.onboarding.domain.usecases.OnboardingUsecase
import kotlinx.coroutines.launch

class OnboardingViewModel(
    initialState: OnboardingState = OnboardingState(),
    private val onboardingUsecase: OnboardingUsecase
) : BaseViewModel<OnboardingState, OnboardingIntent, OnboardingSideEffect>(initialState) {

    init {
        loadOnboardingData()
    }

    override fun onIntent(intent: OnboardingIntent) = reduce(intent)

    override fun reduce(intent: OnboardingIntent) {
        when (intent) {
            OnboardingIntent.OnboardingFinished -> postSideEffect(OnboardingSideEffect.NavigateToLogin)
        }
    }

    private fun loadOnboardingData() {
        viewModelScope.launch {
            onboardingUsecase().collect { result ->
                updateState { copy(onboardingDataUiState = result) }
            }
        }
    }
}
