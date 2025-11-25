package com.cashcove.features.authentication.di

import com.cashcove.features.authentication.data.repository.AuthenticationRepository
import com.cashcove.features.authentication.data.repository.AuthenticationRepositoryImpl
import com.cashcove.features.authentication.data.service.AuthenticationService
import com.cashcove.features.authentication.screens.login.domain.usecases.LoginUsecase
import com.cashcove.features.authentication.screens.login.presentation.LoginViewModel
import com.cashcove.features.authentication.screens.onboarding.domain.usecases.OnboardingUsecase
import com.cashcove.features.authentication.screens.onboarding.presentation.OnboardingViewModel
import com.cashcove.features.authentication.screens.otp.domain.usecase.OtpConfirmUsecase
import com.cashcove.features.authentication.screens.otp.presentation.OtpViewModel
import com.cashcove.features.authentication.screens.register.domain.usecase.RegisterUsecase
import com.cashcove.features.authentication.screens.register.presentation.RegisterViewModel
import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val AuthenticationModule = module {
    single<AuthenticationService> {
        // todo: fix deprecated `create` method
        get<Ktorfit>().create()
    }

    single<AuthenticationRepository> {
        AuthenticationRepositoryImpl(
            authenticationService = get(),
            dependencies = get()
        )
    }

    // Use cases
    factory { LoginUsecase(repository = get()) }
    factory { RegisterUsecase(repository = get()) }
    factory { OnboardingUsecase(repository = get()) }
    factory { OtpConfirmUsecase(repository = get()) }

    // ViewModels
    viewModel { LoginViewModel(loginUsecase = get()) }
    viewModel { RegisterViewModel(registerUsecase = get()) }
    viewModel { OnboardingViewModel(onboardingUsecase = get()) }
    viewModel { OtpViewModel(otpConfirmUsecase = get()) }
}
