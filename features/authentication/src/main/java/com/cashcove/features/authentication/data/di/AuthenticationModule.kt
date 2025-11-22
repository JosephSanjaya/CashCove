package com.cashcove.features.authentication.data.di

import com.cashcove.features.authentication.data.repository.AuthenticationRepository
import com.cashcove.features.authentication.data.repository.AuthenticationRepositoryImpl
import com.cashcove.features.authentication.data.service.AuthenticationService
import com.cashcove.features.authentication.presentation.login.LoginState
import com.cashcove.features.authentication.presentation.login.LoginViewModel
import com.cashcove.features.authentication.presentation.onbording.OnBoardingState
import com.cashcove.features.authentication.presentation.onbording.OnBoardingViewModel
import com.cashcove.features.authentication.presentation.otp.OtpState
import com.cashcove.features.authentication.presentation.otp.OtpViewModel
import com.cashcove.features.authentication.presentation.register.RegisterState
import com.cashcove.features.authentication.presentation.register.RegisterViewModel
import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val AuthenticationModule = module {
    single<AuthenticationService> {
        val ktorfit: Ktorfit = get()
        ktorfit.create()
    }

    single<AuthenticationRepository> {
        AuthenticationRepositoryImpl(
            authenticationService = get(),
            dependencies = get()
        )
    }

    viewModel {
        LoginViewModel(
            initialState = LoginState(),
            repository = get()
        )
    }

    viewModel {
        RegisterViewModel(
            initialState = RegisterState(),
            repository = get()
        )
    }

    viewModel {
        OnBoardingViewModel(OnBoardingState())
    }

    viewModel {
        OtpViewModel(OtpState())
    }
}
