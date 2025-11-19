package com.cashcove.features.authentication.presentation.register

import com.cashcove.core.viewmodel.BaseViewModel

class RegisterViewModel(initialState: Unit) :
    BaseViewModel<Unit, RegisterIntent, RegisterSideEffect>(initialState) {
    override fun onIntent(intent: RegisterIntent) = postSideEffect(
        when (intent) {
            RegisterIntent.NavigateToLogin -> RegisterSideEffect.NavigateToLogin
            RegisterIntent.NavigateToMain -> RegisterSideEffect.NavigateToMain
        }
    )
}
