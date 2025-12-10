package com.cashcove.features.auth.register.di

import com.cashcove.core.di.RepositoryDependencies
import com.cashcove.features.auth.register.data.repository.RegisterRepository
import com.cashcove.features.auth.register.data.repository.RegisterRepositoryImpl
import com.cashcove.features.auth.register.data.service.RegisterService
import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
object RegisterModule {

    @Single
    fun provideRegisterService(
        ktorfit: Ktorfit
    ): RegisterService {
        return ktorfit.create<RegisterService>()
    }

    @Single
    fun provideRegisterRepository(
        registerService: RegisterService,
        dependencies: RepositoryDependencies
    ): RegisterRepository {
        return RegisterRepositoryImpl(
            registerService = registerService,
            dependencies = dependencies
        )
    }
}