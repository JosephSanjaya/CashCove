package com.cashcove.core.common.constants

object AppConstants {
    private const val ONE_SECOND_MILLIS = 1_000L // in millis

    object Network {
        const val BASE_URL = ""
    }

    object Database {
        const val DATABASE_NAME = "cashcove_db"
        const val DATABASE_VERSION = 1
    }

    object Authentication {
        const val PASSWORD_MIN_LENGTH = 8
        const val AUTH_PREFERENCES_NAME = "auth_preferences"
    }

    object DataStore {
        const val USER_PREFERENCES_NAME = "user_prefs"
    }

    object Times {
        // Timeout values in seconds (used with TimeUnit.SECONDS)
        const val GENERAL_REQUEST_TIMEOUT = 30L
        const val REQUEST_READ_TIMEOUT = 30L
        const val REQUEST_WRITE_TIMEOUT = 30L
        const val LOGIN_REQUEST_TIMEOUT = 20L
    }

    object RegexPatterns {
        const val PHONE_NUMBER = """^09\d{9}$"""
    }
}