package com.cashcove.features.auth.login.di

import com.cashcove.features.auth.login.data.repository.LoginRepository
import com.cashcove.features.auth.login.data.repository.LoginRepositoryImpl
import com.cashcove.features.auth.login.data.service.LoginService
import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.dsl.module
val LoginModule = module {

    single<LoginService>{
        get<Ktorfit>.create()
    }

    single<LoginRepository> {
        LoginRepositoryImpl(
            loginService = get(),
            dependencies = get()
        )
    }
}