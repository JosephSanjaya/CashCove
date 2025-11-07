package com.cashcove.core.network.api

import com.cashcove.core.network.error.NetworkError
import com.cashcove.core.network.error.ErrorHandler
import com.cashcove.core.network.util.ConnectivityChecker
import com.cashcove.core.logger.Logger

/**
 * Base class for API services that provides common error handling and connectivity checking
 */
abstract class BaseApiService(
    protected val errorHandler: ErrorHandler,
    protected val connectivityChecker: ConnectivityChecker,
    protected val logger: Logger
) {
    
    /**
     * Execute a network call with error handling and connectivity checking
     */
    protected suspend fun <T> executeCall(
        call: suspend () -> T
    ): Result<T> {
        return try {
            // Check connectivity before making the call
            if (!connectivityChecker.isConnected()) {
                logger.w("BaseApiService", "No internet connection available")
                return Result.failure(NetworkError.NoInternetConnection)
            }
            
            val result = call()
            Result.success(result)
        } catch (e: Exception) {
            val networkError = errorHandler.handleError(e)
            Result.failure(networkError)
        }
    }
}

