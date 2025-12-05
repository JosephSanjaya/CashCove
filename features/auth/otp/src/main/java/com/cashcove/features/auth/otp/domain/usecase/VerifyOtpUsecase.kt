package com.cashcove.features.auth.otp.domain.usecase

import com.cashcove.core.common.model.RepositoryBaseResult
import com.cashcove.core.common.model.UsecaseBaseResult
import com.cashcove.core.common.utils.extensions.validateOtpCode
import com.cashcove.core.datastore.AuthPreferencesManager
import com.cashcove.core.datastore.UserPreferencesManager
import com.cashcove.features.auth.otp.data.model.VerifyOtpRequestDTO
import com.cashcove.features.auth.otp.data.repository.OtpRepository
import com.cashcove.features.auth.otp.domain.model.OtpError
import com.cashcove.features.auth.otp.domain.model.OtpResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class VerifyOtpUsecase(
    private val repository: OtpRepository,
    private val userPreferencesManager: UserPreferencesManager,
    private val authPreferencesManager: AuthPreferencesManager,
) {

    suspend operator fun invoke(otp: String): Flow<UsecaseBaseResult<OtpResult>> = flow {
        emit(UsecaseBaseResult.Loading)
        val phoneNumber = userPreferencesManager.phoneNumberFlow.first()
        if (phoneNumber.isBlank()) {
            emit(UsecaseBaseResult.Error(OtpError.UserDataEmpty))
            return@flow
        }
        if (!otp.validateOtpCode()) {
            emit(UsecaseBaseResult.Error(OtpError.InvalidOtp))
            return@flow
        }
        val otpRequest = VerifyOtpRequestDTO(
            phoneNumber = phoneNumber,
            otp = otp
        )
        when (val result = repository.verifyOtp(otpRequest)) {
            is RepositoryBaseResult.Error -> {
                emit(UsecaseBaseResult.Error(result.exception))
                return@flow
            }

            is RepositoryBaseResult.Success -> {
                val response = result.data

                if (response.error != null) {
                    emit(UsecaseBaseResult.Error(Exception(response.error.message)))
                    return@flow
                }

                val responseData = response.data
                if (responseData == null) {
                    emit(UsecaseBaseResult.Error(OtpError.EmptyResponse))
                    return@flow
                }

                val tokens = responseData.tokens
                if (tokens.accessToken.isBlank() || tokens.refreshToken.isBlank()) {
                    emit(UsecaseBaseResult.Error(OtpError.TokensAreEmpty))
                    return@flow
                }

                val user = responseData.user
                if (user.id.isBlank() || user.phoneNumber.isBlank()) {
                    emit(UsecaseBaseResult.Error(OtpError.EmptyResponse))
                    return@flow
                }

                val otpResult = OtpResult(
                    phoneNumber = user.phoneNumber,
                    userId = user.id,
                    accessToken = tokens.accessToken,
                    refreshToken = tokens.refreshToken
                )

                authPreferencesManager.saveAuthData(
                    accessToken = tokens.accessToken,
                    refreshToken = tokens.refreshToken,
                    userId = user.id
                )

                emit(UsecaseBaseResult.Success(otpResult))
            }
        }
    }
}