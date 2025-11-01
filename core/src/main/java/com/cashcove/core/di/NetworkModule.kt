package com.cashcove.core.di

import com.cashcove.core.common.AppConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import java.util.concurrent.TimeUnit

@Module
object NetworkModule {

    @Single
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .connectTimeout(AppConstants.Times.GENERAL_REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(AppConstants.Times.REQUEST_READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(AppConstants.Times.REQUEST_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()

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
}
