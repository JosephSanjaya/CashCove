package com.cashcove.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.cashcove.core.common.constants.DataStoreConstants
import com.cashcove.core.datastore.AuthPreferencesManager
import com.cashcove.core.datastore.UserPreferencesManager
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Module
object DataStoreModule {

    @Single
    fun provideUserPreferencesDataStore(
        context: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile(DataStoreConstants.USER_PREFERENCES_NAME)
            }
        )
    }

    @Single
    @Named("auth")
    fun provideAuthPreferencesDataStore(
        context: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile(DataStoreConstants.AUTH_PREFERENCES_NAME)
            }
        )
    }

    @Single
    fun provideUserPreferencesManager(
        dataStore: DataStore<Preferences>
    ): UserPreferencesManager {
        return UserPreferencesManager(dataStore)
    }

    @Single
    fun provideAuthPreferencesManager(
        @Named("auth") authDataStore: DataStore<Preferences>
    ): AuthPreferencesManager {
        return AuthPreferencesManager(authDataStore)
    }
}
