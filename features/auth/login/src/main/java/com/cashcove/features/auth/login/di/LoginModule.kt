package com.cashcove.features.auth.login.di

import com.cashcove.features.auth.login.data.repository.LoginRepository
import com.cashcove.features.auth.login.data.repository.LoginRepositoryImpl
import com.cashcove.features.auth.login.data.service.LoginService
import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
object LoginModule {

    @Single
    fun provideLoginService(
        ktorfit: Ktorfit
    ): LoginService {
        return ktorfit.create<LoginService>()
    }

    @Single
    fun provideLoginRepository(
        loginService: LoginService,
        dependencies: com.cashcove.core.di.RepositoryDependencies
    ): LoginRepository {
        return LoginRepositoryImpl(
            loginService = loginService,
            dependencies = dependencies
        )
    }
}