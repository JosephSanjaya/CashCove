package com.cashcove.auth.onboarding.presentation

import com.cashcove.core.viewmodel.BaseViewModel

class OnBoardingViewModel(initialState: OnBoardingState) :
    BaseViewModel<OnBoardingState, OnBoardingIntent, OnBoardingSideEffect>(initialState) {

    fun onIntent(intent: OnBoardingIntent) = reduce(intent)

    override fun reduce(intent: OnBoardingIntent) {
        when (intent) {
            OnBoardingIntent.OnboardingFinished -> postSideEffect(OnBoardingSideEffect.NavigateToLogin)
        }
    }
}
