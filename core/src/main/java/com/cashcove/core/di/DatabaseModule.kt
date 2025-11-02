package com.cashcove.core.di

import android.content.Context
import androidx.room.Room
import com.cashcove.core.common.AppConstants
import com.cashcove.core.database.CashCoveDatabase
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
object DatabaseModule {
    @Single
    fun provideDatabase(context: Context): CashCoveDatabase =
        Room.databaseBuilder(
            context,
            CashCoveDatabase::class.java,
            AppConstants.Database.DATABASE_NAME
        )
            .fallbackToDestructiveMigration(true)
            .build()
}
