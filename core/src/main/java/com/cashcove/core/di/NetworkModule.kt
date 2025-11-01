package com.cashcove.core.di
//
// @Module
// @InstallIn(SingletonComponent::class)
// object NetworkModule {
//    @Provides
//    @Singleton
//    fun provideHttpLoggingInterceptor(networkLoggingInterceptor: NetworkLoggingInterceptor): HttpLoggingInterceptor =
//        networkLoggingInterceptor.create()
//
//    @Provides
//    @Singleton
//    fun provideOkHttpClient(
//        requestInterceptor: RequestInterceptor,
//        loggingInterceptor: HttpLoggingInterceptor,
//    ): OkHttpClient =
//        OkHttpClient
//            .Builder()
//            .addInterceptor(requestInterceptor)
//            .addInterceptor(loggingInterceptor)
//            .connectTimeout(AppConstants.Times.GENERAL_REQUEST_TIMEOUT, TimeUnit.SECONDS)
//            .readTimeout(AppConstants.Times.REQUEST_READ_TIMEOUT, TimeUnit.SECONDS)
//            .writeTimeout(AppConstants.Times.REQUEST_WRITE_TIMEOUT, TimeUnit.SECONDS)
//            .retryOnConnectionFailure(true)
//            .build()
//
//    @Provides
//    @Singleton
//    fun provideGson(): Gson =
//        GsonBuilder()
//            .create()
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(
//        okHttpClient: OkHttpClient,
//        gson: Gson,
//    ): Retrofit =
//        Retrofit
//            .Builder()
//            .baseUrl(AppConstants.Network.BASE_URL)
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//
//    @Provides
//    @Singleton
//    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
// }
