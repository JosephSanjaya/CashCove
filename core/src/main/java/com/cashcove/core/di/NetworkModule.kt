package com.cashcove.core.di

import android.content.Context
import com.cashcove.core.common.AppConstants
import com.cashcove.core.datastore.AuthPreferencesManager
import com.cashcove.core.logger.Logger
import com.cashcove.core.network.error.ErrorHandler
import com.cashcove.core.network.interceptor.AuthInterceptor
import com.cashcove.core.network.interceptor.LogInterceptor
import com.cashcove.core.network.util.ConnectivityChecker
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
object NetworkModule {

    @Single
    fun provideLoggingInterceptor(
        logger: Logger
    ): LogInterceptor {
        return LogInterceptor(logger)
    }

    @Single
    fun provideAuthInterceptor(
        authPreferencesManager: AuthPreferencesManager
    ): AuthInterceptor {
        return AuthInterceptor(authPreferencesManager)
    }

    @Single
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        logInterceptor: LogInterceptor
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .connectTimeout(AppConstants.Times.GENERAL_REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(AppConstants.Times.REQUEST_READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(AppConstants.Times.REQUEST_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(authInterceptor)
            .addInterceptor(logInterceptor)
            .build()
    }

    @Single
    fun provideGson(): Gson =
        GsonBuilder()
            .create()

    @Single
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson,
    ): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(AppConstants.Network.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Single
    fun provideErrorHandler(
        logger: Logger
    ): ErrorHandler {
        return ErrorHandler(logger)
    }

    @Single
    fun provideConnectivityChecker(
        context: Context
    ): ConnectivityChecker {
        return ConnectivityChecker(context)
    }
}
