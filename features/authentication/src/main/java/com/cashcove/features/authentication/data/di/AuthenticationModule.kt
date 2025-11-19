package com.cashcove.features.authentication.data.di

import com.cashcove.features.authentication.presentation.login.LoginViewModel
import com.cashcove.features.authentication.presentation.onbording.OnBoardingViewModel
import com.cashcove.features.authentication.presentation.register.RegisterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val AuthenticationModule = module {
    viewModel {
        LoginViewModel(Unit)
        RegisterViewModel(Unit)
        OnBoardingViewModel(Unit)
    }
}
