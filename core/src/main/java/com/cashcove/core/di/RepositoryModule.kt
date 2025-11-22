package com.cashcove.core.di

import com.cashcove.core.common.constants.NetworkConstants
import com.cashcove.core.logger.Logger
import com.cashcove.core.network.error.ErrorHandler
import com.cashcove.core.network.util.ConnectivityChecker
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

/**
 * Module that provides repository-related dependencies.
 * This module provides all common dependencies needed by repositories,
 * following Android architecture best practices.
 */
@Module(includes = [NetworkModule::class, LoggerModule::class])
object RepositoryModule {

    /**
     * Provides repository dependencies as a single object.
     * This simplifies repository creation in feature modules by bundling
     * all BaseRepository dependencies together.
     * 
     * Following Android architecture best practices, this centralizes
     * all common repository dependencies in one place.
     */
    @Single
    fun provideRepositoryDependencies(
        errorHandler: ErrorHandler,
        connectivityChecker: ConnectivityChecker,
        logger: Logger
    ): RepositoryDependencies {
        return RepositoryDependencies(errorHandler, connectivityChecker, logger)
    }

    /**
     * Provides a shared Ktorfit instance for creating API services.
     * This centralizes the HTTP client configuration and makes it reusable
     * across all feature modules.
     * 
     * Benefits:
     * - Single source of truth for API configuration
     * - Consistent JSON serialization settings
     * - Reuses OkHttpClient with interceptors
     * - No need to duplicate Ktorfit setup in feature modules
     */
    @Single
    fun provideKtorfit(
        okHttpClient: OkHttpClient
    ): Ktorfit {
        val httpClient = HttpClient(OkHttp) {
            engine {
                preconfigured = okHttpClient
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    encodeDefaults = false
                    prettyPrint = false
                    coerceInputValues = true
                })
            }
        }
        
        return Ktorfit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .httpClient(httpClient)
            .build()
    }
}

/**
 * Data class that holds the dependencies required by BaseRepository.
 * This simplifies dependency injection for repositories.
 * 
 * Following the Dependency Inversion Principle, this bundles all
 * infrastructure dependencies that repositories need.
 */
data class RepositoryDependencies(
    val errorHandler: ErrorHandler,
    val connectivityChecker: ConnectivityChecker,
    val logger: Logger
)

