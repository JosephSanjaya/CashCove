package com.cashcove.auth.onboarding.presentation

sealed interface OnBoardingSideEffect {
    data object NavigateToRegister : OnBoardingSideEffect
    data object NavigateToLogin : OnBoardingSideEffect
}
