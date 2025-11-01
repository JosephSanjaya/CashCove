package com.cashcove.app

import android.app.Application
import com.cashcove.core.di.LoggerModule
import com.cashcove.core.di.NetworkModule
import com.tencent.mmkv.BuildConfig
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class CashCoveApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (BuildConfig.DEBUG) {
                // setup logger
            }
            modules(
                LoggerModule.module,
                NetworkModule.module,
            )
        }
    }
}
