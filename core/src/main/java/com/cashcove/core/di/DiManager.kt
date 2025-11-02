package com.cashcove.core.di

import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

object DiManager {
    fun startInitModules() {
        startKoin {
            modules(
                LoggerModule.module,
                NetworkModule.module,
            )
        }
    }
}
