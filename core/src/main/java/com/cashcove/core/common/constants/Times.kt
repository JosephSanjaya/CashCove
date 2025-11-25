package com.cashcove.core.common.constants

object TimesConstants {
    private const val ONE_SECOND_MILLIS = 1_000L // in millis

    // Timeout values in seconds (used with TimeUnit.SECONDS)
    const val GENERAL_REQUEST_TIMEOUT = 30L
    const val REQUEST_READ_TIMEOUT = 30L
    const val REQUEST_WRITE_TIMEOUT = 30L
    const val LOGIN_REQUEST_TIMEOUT = 20L
}
