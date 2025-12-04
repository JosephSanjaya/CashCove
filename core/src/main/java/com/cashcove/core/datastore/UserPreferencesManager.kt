package com.cashcove.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class UserPreferences(
    val isDarkMode: Boolean = false,
    val username: String = "Guest",
    val phoneNumber: String = ""
)

class UserPreferencesManager(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")
        private val USERNAME_KEY = stringPreferencesKey("username")
        private val IS_FIRST_LAUNCH_KEY = booleanPreferencesKey("is_first_launch")
        private val PHONE_NUMBER_KEY = stringPreferencesKey("phone_number")
    }

    fun getUserPreferences(): Flow<UserPreferences> {
        return dataStore.data.map { preferences ->
            UserPreferences(
                isDarkMode = preferences[DARK_MODE_KEY] ?: false,
                username = preferences[USERNAME_KEY] ?: "Guest",
                phoneNumber = preferences[PHONE_NUMBER_KEY] ?: ""
            )
        }
    }

    suspend fun saveUserPreferences(
        isDarkMode: Boolean,
        username: String,
        phoneNumber: String
    ) {
        dataStore.edit { preferences ->
            preferences[DARK_MODE_KEY] = isDarkMode
            preferences[USERNAME_KEY] = username
            preferences[PHONE_NUMBER_KEY] = phoneNumber
        }
    }

    suspend fun updateDarkMode(isDarkMode: Boolean) {
        dataStore.edit { preferences ->
            preferences[DARK_MODE_KEY] = isDarkMode
        }
    }

    suspend fun updateUsername(username: String) {
        dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = username
        }
    }

    suspend fun updatePhoneNumber(phoneNumber: String) {
        dataStore.edit { preferences ->
            preferences[PHONE_NUMBER_KEY] = phoneNumber
        }
    }

    suspend fun clearUserPreferences() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    fun isFirstLaunch(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[IS_FIRST_LAUNCH_KEY] ?: true
        }
    }

    suspend fun setFirstLaunchCompleted() {
        dataStore.edit { preferences ->
            preferences[IS_FIRST_LAUNCH_KEY] = false
        }
    }
}
