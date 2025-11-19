package com.cashcove.features.authentication.presentation.onbording

import com.cashcove.core.viewmodel.BaseViewModel

class OnBoardingViewModel(initialState: Unit) :
    BaseViewModel<Unit, OnBoardingIntent, OnBoardingSideEffect>(initialState) {
    override fun onIntent(intent: OnBoardingIntent) = postSideEffect(
        when (intent) {
            OnBoardingIntent.NavigateToLogin -> OnBoardingSideEffect.NavigateToLogin
            OnBoardingIntent.NavigateToRegister -> OnBoardingSideEffect.NavigateToRegister
        }
    )
}
