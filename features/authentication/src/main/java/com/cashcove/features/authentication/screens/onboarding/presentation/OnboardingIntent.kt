package com.cashcove.features.authentication.screens.onboarding.presentation

sealed interface OnboardingIntent {
    data object OnboardingFinished : OnboardingIntent
}
