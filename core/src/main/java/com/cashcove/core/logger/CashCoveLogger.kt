package com.cashcove.core.logger

import android.util.Log

class CashCoveLogger : Logger {

    companion object {
        private const val TAG = "CashCoveApp"
    }

    private enum class LogLevel {
        Debug, Info, Warning, Error
    }

    private fun log(message: String, logLevel: LogLevel, tag: String? = null) {
        when (logLevel) {
            LogLevel.Debug -> Log.d(tag ?: TAG, message)
            LogLevel.Info -> Log.i(tag ?: TAG, message)
            LogLevel.Warning -> Log.w(tag ?: TAG, message)
            LogLevel.Error -> Log.e(tag ?: TAG, message)
        }
    }

    override fun d(message: String) = log(message, LogLevel.Debug)

    override fun d(tag: String, message: String) = log(message, LogLevel.Debug, tag)

    override fun i(message: String) = log(message, LogLevel.Info)

    override fun i(tag: String, message: String) = log(message, LogLevel.Info, tag)

    override fun w(message: String) = log(message, LogLevel.Warning)

    override fun w(tag: String, message: String) = log(message, LogLevel.Info, tag)

    override fun e(message: String, throwable: Throwable?) = log(message, LogLevel.Error)

    override fun e(tag: String, message: String, throwable: Throwable?) =
        log(message, LogLevel.Error, tag)
}
