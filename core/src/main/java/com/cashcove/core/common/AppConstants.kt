package com.cashcove.core.common

object AppConstants {
    private const val ONE_SECOND_MILLIS = 1_000L // in millis

    object Network {
        const val BASE_URL = ""
    }

    object Database {
        const val DATABASE_NAME = ""
        const val DATABASE_VERSION = 1
    }

    object Authentication {
        const val PASSWORD_MIN_LENGTH = 8
        const val AUTH_PREFERENCES_NAME = "auth_preferences"
    }

    object Times {
        const val GENERAL_REQUEST_TIMEOUT = 30 * ONE_SECOND_MILLIS
        const val REQUEST_READ_TIMEOUT = 30 * GENERAL_REQUEST_TIMEOUT
        const val REQUEST_WRITE_TIMEOUT = 30 * GENERAL_REQUEST_TIMEOUT
        const val LOGIN_REQUEST_TIMEOUT = 20 * ONE_SECOND_MILLIS
    }

    object RegexPatterns {
        const val PHONE_NUMBER = """^09\d{9}$"""
    }
}
