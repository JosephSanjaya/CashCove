package com.cashcove.core.network.interceptor

import com.cashcove.core.datastore.AuthPreferencesManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val authPreferencesManager: AuthPreferencesManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // Get token synchronously (using runBlocking for OkHttp interceptor)
        val token = runBlocking {
            authPreferencesManager.getAuthPreferences().first().token
        }

        // If no token, proceed without authorization header
        val newRequest = if (token != null) {
            originalRequest.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
        } else {
            originalRequest
        }

        return chain.proceed(newRequest)
    }
}
