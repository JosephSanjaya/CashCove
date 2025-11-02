package com.cashcove.app

import android.app.Application
import com.cashcove.core.di.DiManager

class CashCoveApp : Application() {
    override fun onCreate() {
        super.onCreate()
        DiManager.startInitModules(this)
    }
}
