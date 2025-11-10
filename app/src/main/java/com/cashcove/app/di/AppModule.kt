package com.cashcove.app.di

import com.cashcove.app.splash.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    viewModel {
        SplashViewModel(
            authPreferencesManager = get(),
            userPreferencesManager = get()
        )
    }
}
