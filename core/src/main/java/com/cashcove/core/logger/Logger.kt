package com.cashcove.core.logger

interface Logger {
    fun d(message: String)
    fun d(tag: String, message: String)
    fun i(message: String)
    fun i(tag: String, message: String)
    fun w(message: String)
    fun w(tag: String, message: String)
    fun e(message: String, throwable: Throwable? = null)
    fun e(tag: String, message: String, throwable: Throwable? = null)
}
