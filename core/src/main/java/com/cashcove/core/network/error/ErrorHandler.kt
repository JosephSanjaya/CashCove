package com.cashcove.core.network.error

import com.cashcove.core.logger.Logger
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorHandler(
    private val logger: Logger
) {

    fun handleError(throwable: Throwable): NetworkError {
        return when (throwable) {
            is HttpException -> {
                val code = throwable.code()
                val errorBody = throwable.response()?.errorBody()
                val message = parseErrorBody(errorBody) ?: throwable.message()
                
                logger.e("ErrorHandler", "HTTP Error: $code - $message")
                
                NetworkError.HttpError(
                    code = code,
                    message = message
                )
            }
            
            is SocketTimeoutException -> {
                logger.e("ErrorHandler", "Timeout error: ${throwable.message}")
                NetworkError.Timeout
            }
            
            is UnknownHostException, is IOException -> {
                logger.e("ErrorHandler", "Network error: ${throwable.message}")
                NetworkError.NoInternetConnection
            }
            
            else -> {
                logger.e("ErrorHandler", "Unknown error: ${throwable.message}", throwable)
                NetworkError.UnknownError(
                    message = throwable.message,
                    cause = throwable
                )
            }
        }
    }

    private fun parseErrorBody(errorBody: ResponseBody?): String? {
        return try {
            errorBody?.string()
        } catch (e: Exception) {
            logger.e("ErrorHandler", "Failed to parse error body", e)
            null
        }
    }
}

