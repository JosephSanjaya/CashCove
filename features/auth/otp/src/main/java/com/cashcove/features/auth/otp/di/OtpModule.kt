package com.cashcove.features.auth.otp.di

import com.cashcove.core.datastore.AuthPreferencesManager
import com.cashcove.core.datastore.UserPreferencesManager
import com.cashcove.features.auth.otp.data.repository.OtpRepository
import com.cashcove.features.auth.otp.data.repository.OtpRepositoryImpl
import com.cashcove.features.auth.otp.data.service.OtpService
import com.cashcove.features.auth.otp.domain.usecase.ResendOtpUsecase
import com.cashcove.features.auth.otp.domain.usecase.VerifyOtpUsecase
import com.cashcove.features.auth.otp.presentation.OtpState
import com.cashcove.features.auth.otp.presentation.OtpViewModel
import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
object OtpModule {

    @Single
    fun provideOtpService(
        ktorfit: Ktorfit
    ): OtpService {
        return ktorfit.create<OtpService>()
    }

    @Single
    fun provideOtpRepository(
        otpService: OtpService,
        dependencies: com.cashcove.core.di.RepositoryDependencies
    ): OtpRepository {
        return OtpRepositoryImpl(
            otpService = otpService,
            dependencies = dependencies
        )
    }

    @Factory
    fun provideVerifyOtpUsecase(
        repository: OtpRepository,
        userPreferencesManager: UserPreferencesManager,
        authPreferencesManager: AuthPreferencesManager
    ): VerifyOtpUsecase {
        return VerifyOtpUsecase(
            repository = repository,
            userPreferencesManager = userPreferencesManager,
            authPreferencesManager = authPreferencesManager
        )
    }

    @Factory
    fun provideResendOtpUsecase(
        repository: OtpRepository,
        userPreferencesManager: UserPreferencesManager
    ): ResendOtpUsecase {
        return ResendOtpUsecase(
            repository = repository,
            userPreferencesManager = userPreferencesManager
        )
    }

    @Factory
    fun provideOtpViewModel(
        verifyOtpUsecase: VerifyOtpUsecase,
        resendOtpUsecase: ResendOtpUsecase
    ): OtpViewModel {
        return OtpViewModel(
            initState = OtpState(),
            resendOtpUsecase = resendOtpUsecase,
            verifyOtpUsecase = verifyOtpUsecase,
        )
    }
}