package com.cashcove.core.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

object DiManager {
    fun startInitModules(context: Context) {
        startKoin {
            androidContext(context)
            modules(
                LoggerModule.module,
                NetworkModule.module,
                DatabaseModule.module,

            )
        }
    }
}
