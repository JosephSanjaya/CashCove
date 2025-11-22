package com.cashcove.features.authentication.presentation.onbording

sealed interface OnBoardingIntent {
    data object OnboardingFinished : OnBoardingIntent
}
