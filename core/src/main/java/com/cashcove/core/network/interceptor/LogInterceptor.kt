package com.cashcove.core.network.interceptor

import com.cashcove.core.logger.Logger
import okhttp3.Interceptor
import okhttp3.Response

class LogInterceptor(
    private val logger: Logger
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        logger.d("Request: ${chain.request().method} ${chain.request().url} ${chain.request().body}")
        val response = chain.proceed(chain.request())
        logger.d("Response: ${response.code} ${response.message} ${response.body}")
        return response
    }
}
