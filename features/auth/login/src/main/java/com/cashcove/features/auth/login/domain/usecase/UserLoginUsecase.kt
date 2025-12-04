package com.cashcove.features.auth.login.domain.usecase

import com.cashcove.core.common.model.RepositoryBaseResult
import com.cashcove.core.common.model.UsecaseBaseResult
import com.cashcove.core.datastore.UserPreferencesManager
import com.cashcove.features.auth.login.data.model.SendOtpRequestDTO
import com.cashcove.features.auth.login.data.repository.LoginRepository
import com.cashcove.features.auth.login.domain.model.Login
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserLoginUsecase(
    private val repository: LoginRepository,
    private val userPreferences: UserPreferencesManager
) {
    suspend operator fun invoke(phoneNumber: String): Flow<UsecaseBaseResult<Login>> =
        flow {
            emit(UsecaseBaseResult.Loading)
            val login = Login(phoneNumber)
            if (login.validate()) {
                when (val result = repository.sendOtp(SendOtpRequestDTO(phoneNumber))) {
                    RepositoryBaseResult.Loading -> emit(UsecaseBaseResult.Loading)
                    is RepositoryBaseResult.Success -> {
                        userPreferences.updatePhoneNumber(phoneNumber)
                        emit(UsecaseBaseResult.Success(data = login))
                    }

                    is RepositoryBaseResult.Error -> emit(
                        UsecaseBaseResult.Error(
                            Exception(result.exception.message ?: "something went wrong!")
                        )
                    )
                }
            } else {
                emit(UsecaseBaseResult.Error(Exception("phone number validation error!")))
            }
        }
}