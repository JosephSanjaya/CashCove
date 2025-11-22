package com.cashcove.features.authentication.presentation.register

import com.cashcove.core.viewmodel.BaseViewModel
import com.cashcove.features.authentication.data.repository.AuthenticationRepository

class RegisterViewModel(
    initialState: RegisterState,
    private val repository: AuthenticationRepository
) : BaseViewModel<RegisterState, RegisterIntent, RegisterSideEffect>(initialState) {
    override fun onIntent(intent: RegisterIntent) = postSideEffect(
        when (intent) {
            RegisterIntent.NavigateToLogin -> RegisterSideEffect.NavigateToLogin
            RegisterIntent.NavigateToMain -> RegisterSideEffect.NavigateToMain
        }
    )
}
