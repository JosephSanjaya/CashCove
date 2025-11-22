package com.cashcove.core.common.repository

import com.cashcove.core.common.model.UiState
import com.cashcove.core.common.utils.network.safeApiCall
import com.cashcove.core.di.RepositoryDependencies

/**
 * Base repository class that provides common functionality for all repositories.
 * This class encapsulates the dependencies needed for safeApiCall, allowing
 * repositories to use safeApiCall without passing dependencies each time.
 */
abstract class BaseRepository(
    private val dependencies: RepositoryDependencies
) {
    /**
     * Executes an API call safely with error handling and connectivity checking.
     * This method wraps the safeApiCall function with the repository's dependencies.
     *
     * @param apiCall The suspend function that represents the API call
     * @return UiState<T> - Either Success with data or Error with message
     */
    protected suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): UiState<T> {
        return safeApiCall(
            errorHandler = dependencies.errorHandler,
            connectivityChecker = dependencies.connectivityChecker,
            logger = dependencies.logger,
            apiCall = apiCall
        )
    }
}

