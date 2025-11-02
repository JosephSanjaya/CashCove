package com.cashcove.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cashcove.core.common.AppConstants
import com.cashcove.core.database.dao.UserDao
import com.cashcove.core.database.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
    ],
    version = AppConstants.Database.DATABASE_VERSION,
    exportSchema = false,
)
@TypeConverters()
abstract class CashCoveDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
