package com.cashcove.core.di

// @Module
// @InstallIn(SingletonComponent::class)
// object DatabaseModule {
//    @Provides
//    @Singleton
//    fun provideDatabase(
//        @ApplicationContext context: Context,
//    ): AppDatabase =
//        Room
//            .databaseBuilder(context, AppDatabase::class.java, AppDatabase.Companion.DB_NAME)
//            .build()
//
//    @Provides
//    fun provideExpenseDao(database: AppDatabase): ExpenseDao = database.expenseDao()
//
//    @Provides
//    fun provideParticipantsDao(database: AppDatabase): ParticipantsDao = database.participantsDao()
// }
