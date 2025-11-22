package com.cashcove.core.common.utils.network

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
 * @return UiState<T> - Either Success with data or Error with message
 */
suspend fun <T> safeApiCall(
    errorHandler: ErrorHandler,
    connectivityChecker: ConnectivityChecker,
    logger: Logger,
    apiCall: suspend () -> T
): UiState<T> {
    return try {
        // Check connectivity before making the call
        if (!connectivityChecker.isConnected()) {
            logger.w("SafeApiCall", "No internet connection available")
            return UiState.Error("No internet connection available")
        }

        // Execute the API call
        val result = apiCall()
        UiState.Success(result)
    } catch (e: Exception) {
        // Handle the error using ErrorHandler
        val networkError = errorHandler.handleError(e)

        // Extract error message from NetworkError
        val errorMessage = when (networkError) {
            is NetworkError.HttpError -> {
                networkError.message ?: "HTTP Error: ${networkError.code}"
            }
            is NetworkError.NetworkException -> {
                networkError.message ?: "Network error occurred"
            }
            is NetworkError.UnknownError -> {
                networkError.message ?: "An unknown error occurred"
            }
            is NetworkError.NoInternetConnection -> {
                "No internet connection available"
            }
            is NetworkError.Timeout -> {
                "Request timeout. Please try again"
            }
        }

        logger.e("SafeApiCall", "API call failed: $errorMessage", e)
        UiState.Error(errorMessage)
    }
}
