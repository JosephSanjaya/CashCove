package com.cashcove.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class AuthPreferences(
    val isLoggedIn: Boolean = false,
    val token: String? = null,
    val userId: String? = null
)

class AuthPreferencesManager(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        private val IS_LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val USER_ID_KEY = stringPreferencesKey("user_id")
    }

    fun getAuthPreferences(): Flow<AuthPreferences> {
        return dataStore.data.map { preferences ->
            AuthPreferences(
                isLoggedIn = preferences[IS_LOGGED_IN_KEY] ?: false,
                token = preferences[TOKEN_KEY],
                userId = preferences[USER_ID_KEY]
            )
        }
    }

    suspend fun saveAuthData(token: String, userId: String) {
        dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN_KEY] = true
            preferences[TOKEN_KEY] = token
            preferences[USER_ID_KEY] = userId
        }
    }

    suspend fun updateLoginStatus(isLoggedIn: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN_KEY] = isLoggedIn
        }
    }

    suspend fun updateToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN_KEY] = false
            preferences.remove(TOKEN_KEY)
            preferences.remove(USER_ID_KEY)
        }
    }

    suspend fun clearAuthPreferences() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
