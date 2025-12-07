package com.cashcove.app.di

import com.cashcove.app.splash.SplashViewModel
import com.cashcove.core.datastore.AuthPreferencesManager
import com.cashcove.core.datastore.UserPreferencesManager
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
object AppModule {

    @Factory
    fun provideSplashViewModel(
        authPreferencesManager: AuthPreferencesManager,
        userPreferencesManager: UserPreferencesManager
    ): SplashViewModel {
        return SplashViewModel(
            authPreferencesManager = authPreferencesManager,
            userPreferencesManager = userPreferencesManager
        )
    }
}
