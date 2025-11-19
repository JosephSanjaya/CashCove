package com.cashcove.features.authentication.presentation.login

sealed interface LoginSideEffect {
    data object NavigateToRegister : LoginSideEffect
    data object NavigateToMain : LoginSideEffect
}
