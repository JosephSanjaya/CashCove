package com.cashcove.features.authentication.data.di

import com.cashcove.features.authentication.presentation.login.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val AuthenticationModule = module {
    viewModel {
        LoginViewModel(Unit)
    }
}
