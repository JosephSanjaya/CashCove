package com.cashcove.auth.onboarding.di

import com.cashcove.auth.onboarding.presentation.OnBoardingState
import com.cashcove.auth.onboarding.presentation.OnBoardingViewModel
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
object OnboardingModule {

    @Factory
    fun provideOnBoardingViewModel(): OnBoardingViewModel {
        return OnBoardingViewModel(OnBoardingState())
    }
}