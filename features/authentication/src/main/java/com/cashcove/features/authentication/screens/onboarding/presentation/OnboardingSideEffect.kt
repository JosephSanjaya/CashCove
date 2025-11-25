package com.cashcove.features.authentication.screens.onboarding.presentation

sealed interface OnboardingSideEffect {
    data object NavigateToRegister : OnboardingSideEffect
    data object NavigateToLogin : OnboardingSideEffect
}
