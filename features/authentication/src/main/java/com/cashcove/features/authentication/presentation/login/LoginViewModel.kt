package com.cashcove.features.authentication.presentation.login

import com.cashcove.core.viewmodel.BaseViewModel

class LoginViewModel(initState: Unit) :
    BaseViewModel<Unit, LoginIntent, LoginSideEffect>(initState) {
    override fun onIntent(intent: LoginIntent) = postSideEffect(
        when (intent) {
            LoginIntent.NavigateToMain -> LoginSideEffect.NavigateToMain
            LoginIntent.NavigateToRegister -> LoginSideEffect.NavigateToRegister
        }
    )
}
