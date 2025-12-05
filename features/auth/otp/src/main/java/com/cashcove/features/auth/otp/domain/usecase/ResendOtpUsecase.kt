package com.cashcove.features.auth.otp.domain.usecase

import com.cashcove.core.common.model.RepositoryBaseResult
import com.cashcove.core.common.model.UsecaseBaseResult
import com.cashcove.core.common.utils.extensions.validatePhoneNumber
import com.cashcove.core.datastore.AuthPreferencesManager
import com.cashcove.core.datastore.UserPreferencesManager
import com.cashcove.features.auth.otp.data.model.ResendOtpRequestDTO
import com.cashcove.features.auth.otp.data.repository.OtpRepository
import com.cashcove.features.auth.otp.domain.model.OtpError
import com.cashcove.features.auth.otp.domain.model.OtpResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class ResendOtpUsecase(
    private val repository: OtpRepository,
    private val userPreferencesManager: UserPreferencesManager,
) {
    suspend operator fun invoke(otp:String): Flow<UsecaseBaseResult<OtpResult>> = flow {
        emit(UsecaseBaseResult.Loading)
        val phoneNumber:String = userPreferencesManager.phoneNumberFlow.first()
        if (phoneNumber.isNotBlank()){
            val request = ResendOtpRequestDTO(phoneNumber)
         when(val result = repository.resendOtp(request)){
             is RepositoryBaseResult.Error -> emit(UsecaseBaseResult.Error(result.exception))
             is RepositoryBaseResult.Success -> emit(UsecaseBaseResult.Success(OtpResult()))
         }
        }
    }
}