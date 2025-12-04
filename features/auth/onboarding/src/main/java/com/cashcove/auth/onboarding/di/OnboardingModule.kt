package com.cashcove.auth.onboarding.di

import com.cashcove.auth.onboarding.presentation.OnBoardingState
import com.cashcove.auth.onboarding.presentation.OnBoardingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val OnboardingModule = module {
    viewModel {
        OnBoardingViewModel(OnBoardingState())
    }
}