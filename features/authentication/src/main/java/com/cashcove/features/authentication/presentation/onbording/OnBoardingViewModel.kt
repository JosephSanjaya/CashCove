package com.cashcove.features.authentication.presentation.onbording

import com.cashcove.core.viewmodel.BaseViewModel

class OnBoardingViewModel(initialState: Unit) :
    BaseViewModel<Unit, OnBoardingIntent, OnBoardingSideEffect>(initialState) {
    override fun onIntent(intent: OnBoardingIntent) {
        when (intent) {
            OnBoardingIntent.NavigateToRegister ->
                postSideEffect(OnBoardingSideEffect.NavigateToRegister)
        }
    }
}
