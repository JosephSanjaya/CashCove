package com.cashcove.core.di

import android.content.Context
import com.cashcove.core.common.constants.NetworkConstants
import com.cashcove.core.common.constants.TimesConstants
import com.cashcove.core.datastore.AuthPreferencesManager
import com.cashcove.core.logger.Logger
import com.cashcove.core.network.error.ErrorHandler
import com.cashcove.core.network.interceptor.AuthInterceptor
import com.cashcove.core.network.interceptor.LogInterceptor
import com.cashcove.core.network.util.ConnectivityChecker
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.cashcove.core.network.api.ApiService
import okhttp3.OkHttpClient
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module(includes = [LoggerModule::class, DataStoreModule::class])
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
            .connectTimeout(TimesConstants.GENERAL_REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TimesConstants.REQUEST_READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TimesConstants.REQUEST_WRITE_TIMEOUT, TimeUnit.SECONDS)
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
            .baseUrl(NetworkConstants.BASE_URL)
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

    @Single
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
