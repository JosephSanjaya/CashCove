package com.cashcove.features.authentication.presentation.onbording

import com.cashcove.core.viewmodel.BaseViewModel

class OnBoardingViewModel(initialState: OnBoardingState) :
    BaseViewModel<OnBoardingState, OnBoardingIntent, OnBoardingSideEffect>(initialState) {
    override fun onIntent(intent: OnBoardingIntent) = when (intent) {
        OnBoardingIntent.OnboardingFinished -> {
            postSideEffect(OnBoardingSideEffect.NavigateToLogin)
        }
    }
}
