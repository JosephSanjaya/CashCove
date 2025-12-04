package com.cashcove.core.common.utils.network

import com.cashcove.core.common.model.RepositoryBaseResult
import com.cashcove.core.common.model.UiState
import com.cashcove.core.logger.Logger
import com.cashcove.core.network.error.ErrorHandler
import com.cashcove.core.network.error.NetworkError
import com.cashcove.core.network.util.ConnectivityChecker

/**
 * A general function that wraps API calls and handles errors.
 * Returns UiState which can be either Success with data or Error with message.
 *
 * @param errorHandler The error handler to convert exceptions to NetworkError
 * @param connectivityChecker The connectivity checker to verify internet connection
 * @param logger The logger for logging errors and warnings
 * @param apiCall The suspend function that represents the API call
 * @return RepositoryBaseResult<T> - Either Success with data or Error with message
 */
suspend fun <T> safeApiCall(
    errorHandler: ErrorHandler,
    connectivityChecker: ConnectivityChecker,
    logger: Logger,
    apiCall: suspend () -> T
): RepositoryBaseResult<T> {
    return try {
        // Check connectivity before making the call
        if (!connectivityChecker.isConnected()) {
            logger.w("SafeApiCall", "No internet connection available")
            return RepositoryBaseResult.Error(Exception("No internet connection available"))
        }

        // Execute the API call
        val result = apiCall()
        RepositoryBaseResult.Success(result)
    } catch (e: Exception) {
        // Handle the error using ErrorHandler
        val networkError = errorHandler.handleError(e)

        logger.e("SafeApiCall", "API call failed: ${networkError.message}}", e)
        RepositoryBaseResult.Error(networkError)
    }
}
