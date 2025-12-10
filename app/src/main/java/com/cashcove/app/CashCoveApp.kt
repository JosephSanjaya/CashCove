package com.cashcove.app

import android.app.Application
import com.cashcove.app.di.AppModule
import com.cashcove.core.di.DiManager
import com.cashcove.features.auth.register.di.RegisterModule
import com.cashcove.features.auth.onboarding.di.OnboardingModule
import com.cashcove.features.auth.otp.di.OtpModule
import com.cashcove.features.authentication.di.AuthenticationModule
import org.koin.core.context.loadKoinModules

class CashCoveApp : Application() {
    override fun onCreate() {
        super.onCreate()
        DiManager.startInitModules(this)
        loadKoinModules(
            listOf(
                AppModule.module,
                AuthenticationModule.module,
                RegisterModule.module,
                OnboardingModule.module,
                OtpModule.module,
            )
        )
    }
}
