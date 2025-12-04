package com.cashcove.auth.onboarding.presentation

sealed interface OnBoardingIntent {
    data object OnboardingFinished : OnBoardingIntent
}
