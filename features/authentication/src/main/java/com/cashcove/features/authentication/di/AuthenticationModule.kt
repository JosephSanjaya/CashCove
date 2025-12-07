package com.cashcove.features.authentication.di

import com.cashcove.features.authentication.data.repository.AuthenticationRepository
import com.cashcove.features.authentication.data.repository.AuthenticationRepositoryImpl
import com.cashcove.features.authentication.data.service.AuthenticationService
import com.cashcove.features.authentication.domain.usecase.LoginUsecase
import com.cashcove.features.authentication.presentation.login.LoginState
import com.cashcove.features.authentication.presentation.login.LoginViewModel
import com.cashcove.features.authentication.presentation.onbording.OnBoardingState
import com.cashcove.features.authentication.presentation.onbording.OnBoardingViewModel
import com.cashcove.features.authentication.presentation.otp.OtpState
import com.cashcove.features.authentication.presentation.otp.OtpViewModel
import com.cashcove.features.authentication.presentation.register.RegisterState
import com.cashcove.features.authentication.presentation.register.RegisterViewModel
import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
object AuthenticationModule {

    @Single
    fun provideAuthenticationService(
        ktorfit: Ktorfit
    ): AuthenticationService {
        return ktorfit.create<AuthenticationService>()
    }

    @Single
    fun provideAuthenticationRepository(
        authenticationService: AuthenticationService,
        dependencies: com.cashcove.core.di.RepositoryDependencies
    ): AuthenticationRepository {
        return AuthenticationRepositoryImpl(
            authenticationService = authenticationService,
            dependencies = dependencies
        )
    }

    @Factory
    fun provideLoginUsecase(
        repository: AuthenticationRepository
    ): LoginUsecase {
        return LoginUsecase(repository = repository)
    }

    @Factory
    fun provideLoginViewModel(
        loginUsecase: LoginUsecase
    ): LoginViewModel {
        return LoginViewModel(
            initialState = LoginState(),
            loginUsecase = loginUsecase
        )
    }

    @Factory
    fun provideRegisterViewModel(
        repository: AuthenticationRepository
    ): RegisterViewModel {
        return RegisterViewModel(
            initialState = RegisterState(),
            repository = repository
        )
    }

    @Factory
    fun provideOnBoardingViewModel(): OnBoardingViewModel {
        return OnBoardingViewModel(OnBoardingState())
    }

    @Factory
    fun provideOtpViewModel(): OtpViewModel {
        return OtpViewModel(OtpState())
    }
}
