package com.cashcove.core.di

import com.cashcove.core.logger.CashCoveLogger
import com.cashcove.core.logger.Logger
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
object LoggerModule {
    @Single
    fun provideLogger(): Logger = CashCoveLogger()
}
