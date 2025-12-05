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
    val accessToken: String? = null,
    val refreshToken: String? = null,
    val userId: String? = null
)

class AuthPreferencesManager(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        private val IS_LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
        private val USER_ID_KEY = stringPreferencesKey("user_id")
    }

    fun getAuthPreferences(): Flow<AuthPreferences> {
        return dataStore.data.map { preferences ->
            AuthPreferences(
                isLoggedIn = preferences[IS_LOGGED_IN_KEY] ?: false,
                accessToken = preferences[ACCESS_TOKEN_KEY],
                refreshToken = preferences[REFRESH_TOKEN_KEY],
                userId = preferences[USER_ID_KEY]
            )
        }
    }

    val isLoggedInFlow: Flow<Boolean> = dataStore.data.map { it[IS_LOGGED_IN_KEY] ?: false }
    val accessTokenFlow: Flow<String> = dataStore.data.map { it[ACCESS_TOKEN_KEY] ?: "" }
    val refreshTokenFlow: Flow<String> = dataStore.data.map { it[REFRESH_TOKEN_KEY] ?: "" }
    val userIdFlow: Flow<String> = dataStore.data.map { it[USER_ID_KEY] ?: "" }

    suspend fun saveAuthData(accessToken: String, refreshToken: String, userId: String) {
        dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN_KEY] = true
            preferences[ACCESS_TOKEN_KEY] = accessToken
            preferences[REFRESH_TOKEN_KEY] = refreshToken
            preferences[USER_ID_KEY] = userId
        }
    }

    suspend fun updateLoginStatus(isLoggedIn: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN_KEY] = isLoggedIn
        }
    }

    suspend fun updateTokens(accessToken: String, refreshToken: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_KEY] = accessToken
            preferences[REFRESH_TOKEN_KEY] = refreshToken
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN_KEY] = false
            preferences.remove(ACCESS_TOKEN_KEY)
            preferences.remove(REFRESH_TOKEN_KEY)
            preferences.remove(USER_ID_KEY)
        }
    }

    suspend fun clearAuthPreferences() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
