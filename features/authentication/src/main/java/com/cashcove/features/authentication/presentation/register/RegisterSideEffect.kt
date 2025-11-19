package com.cashcove.features.authentication.presentation.register

sealed interface RegisterSideEffect {
    data object NavigateToLogin : RegisterSideEffect
    data object NavigateToMain : RegisterSideEffect
}
